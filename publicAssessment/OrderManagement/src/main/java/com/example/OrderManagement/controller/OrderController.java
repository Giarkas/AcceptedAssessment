package com.example.OrderManagement.controller;

import com.example.OrderManagement.exceptions.OrderNotFoundException;
import com.example.OrderManagement.models.dtos.OrderDTO;
import com.example.OrderManagement.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/orders/{id}")
    public OrderDTO getOrder(@PathVariable long id) {
        log.info("Get order by id {}", id);
        OrderDTO orderDTO = orderService.getOrderById(id);

        if (orderDTO == null) {
            throw new OrderNotFoundException("Order id:" + id);
        }
        return orderService.getOrderById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/orders", consumes = "application/json")
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {

        orderService.placeOrder(orderDTO);

        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/orders/{id}")
    public ResponseEntity deleteOrder(@PathVariable long id) {
        log.info("Delete order by id {}", id);
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/orders/{id}")
    public ResponseEntity<OrderDTO> putOrder(@PathVariable long id, @RequestBody OrderDTO orderDTO) {
        log.info("Update order by id {}", id);
        orderService.updateOrder(id, orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
}
