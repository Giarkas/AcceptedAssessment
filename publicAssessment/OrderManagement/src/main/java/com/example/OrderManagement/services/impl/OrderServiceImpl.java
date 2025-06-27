package com.example.OrderManagement.services.impl;

import com.example.OrderManagement.exceptions.OrderNotFoundException;
import com.example.OrderManagement.models.Orders;
import com.example.OrderManagement.models.OrderLine;
import com.example.OrderManagement.models.OrderLineOrder;
import com.example.OrderManagement.models.OrderLineOrderId;
import com.example.OrderManagement.models.dtos.OrderDTO;
import com.example.OrderManagement.models.dtos.OrderLineDTO;
import com.example.OrderManagement.models.dtos.OrderLogDTO;
import com.example.OrderManagement.repositories.OrderDAO;
import com.example.OrderManagement.repositories.OrderLineDao;
import com.example.OrderManagement.repositories.OrderLineOrderDao;
import com.example.OrderManagement.services.OrderService;
import com.example.OrderManagement.validation.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static OrderDAO orderDAO;
    private static OrderLineOrderDao orderLineOrderDao;
    private static OrderLineDao orderLineDao;
    private static ValidationService validationService;

    public OrderServiceImpl(OrderDAO orderDAO, OrderLineOrderDao orderLineOrderDao, OrderLineDao orderLineDao, ValidationService validationService) {
        this.orderDAO = orderDAO;
        this.orderLineOrderDao = orderLineOrderDao;
        this.orderLineDao = orderLineDao;
        this.validationService = validationService;
    }

    @Override
    public OrderDTO getOrderById(long orderId) {

        Optional<Orders> order = orderDAO.findById(orderId);

        if (order.isPresent()) {

            OrderDTO orderDTO = new OrderDTO();

            orderDTO.fillEntity(order.get());

            List<OrderLineDTO> orderLineDTOList = orderLineOrderDao.findOrderLineByOrder(order.get()).parallelStream().map(
                    olo -> {
                        return new OrderLineDTO().fillEntity(olo);
                    }
            ).collect(Collectors.toList());

            orderDTO.setOrderLines(orderLineDTOList);
            return orderDTO;
        }

        return null;
    }

    @Override
    public void placeOrder(OrderDTO orderDTO) {

        validationService.validateOrder(orderDTO);

        Orders orders = new Orders.Builder().orderDate(orderDTO.getOrderDate()).customerName(orderDTO.getCustomerName()).build();

        orders = orderDAO.save(orders);

        createOrderLines(orders, orderDTO);

    }

    @Override
    @Transactional
    public void deleteOrder(long orderId) {
        Optional<Orders> order = orderDAO.findById(orderId);

        if (order.isEmpty()) {
            throw new OrderNotFoundException("Order not found id: " + orderId);
        }
        List<OrderLine> orderLines = new ArrayList<>(orderLineOrderDao.findOrderLineByOrder(order.get()));

        orderLineOrderDao.deleteAllByOrderId(order.get());

        orderLineDao.deleteByIn(orderLines);

        orderDAO.delete(order.get());
    }

    @Override
    @Transactional
    public void updateOrder(long orderId, OrderDTO orderDTO) {
        Optional<Orders> order = orderDAO.findById(orderId);

        if (order.isEmpty()) {
            throw new OrderNotFoundException("Order not found id: " + orderId);
        }

        order.get().setOrderDate(orderDTO.getOrderDate());
        order.get().setCustomerName(orderDTO.getCustomerName());

        orderDAO.save(order.get());

        deleteOrderLines(order.get());
        createOrderLines(order.get(), orderDTO);

    }

    private static void deleteOrderLines(Orders order) {
        List<OrderLine> orderLines = new ArrayList<>(orderLineOrderDao.findOrderLineByOrder(order));

        orderLineOrderDao.deleteAllByOrderId(order);

        orderLineDao.deleteByIn(orderLines);
    }

    private static void createOrderLines(Orders order, OrderDTO orderDTO) {
        List<OrderLine> orderLines = orderDTO.getOrderLines().stream().map(
                ol -> {
                    OrderLine orderLine = new OrderLine.Builder().price(ol.getPrice()).quantity(ol.getQuantity()).productId(ol.getProductId()).build();
                    return orderLineDao.save(orderLine);
                }
        ).toList();

        for (OrderLine orderLine : orderLines) {
            OrderLineOrder olo = new OrderLineOrder.Builder().id(new OrderLineOrderId(order, orderLine)).build();
            orderLineOrderDao.save(olo);
        }
    }


    @Override
    @Transactional
    public void processOrders() {

        List<OrderLineOrder> orderLineOrders = getUnprocessedOrders();

        if (orderLineOrders.isEmpty())
            return;

        Orders order = orderLineOrders.getFirst().getId().getOrder();
        OrderLogDTO orderLogDTO = new OrderLogDTO.Builder().orderDate(order.getOrderDate()).orderId(order.getOrderId()).build();

        double amount = 0;
        int itemsCount = 0;

        for (OrderLineOrder orderLineOrder : orderLineOrders) {
            amount += (orderLineOrder.getId().getOrderLine().getPrice() * orderLineOrder.getId().getOrderLine().getQuantity());
            itemsCount += orderLineOrder.getId().getOrderLine().getQuantity();
        }
        orderLogDTO.setAmount(amount);
        orderLogDTO.setItemsCount(itemsCount);

        orderDAO.updateToProcessed(order.getOrderId());

        new RestTemplate().postForEntity("http://localhost:8090/orders/logs", orderLogDTO, String.class);

    }

    private List<OrderLineOrder> getUnprocessedOrders() {
        return orderLineOrderDao.getUnprocessedOrders();
    }
}
