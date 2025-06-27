package com.example.OrderManagement.repositories;

import com.example.OrderManagement.models.Orders;
import com.example.OrderManagement.models.OrderLine;
import com.example.OrderManagement.models.OrderLineOrder;
import com.example.OrderManagement.models.OrderLineOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineOrderDao extends JpaRepository<OrderLineOrder, OrderLineOrderId> {

    @Query("select olo.id.orderLine from OrderLineOrder olo where olo.id.orders = ?1")
    List<OrderLine> findOrderLineByOrder(Orders orders);
    @Modifying
    @Query("delete from OrderLineOrder olo where olo.id.orders = ?1")
    void deleteAllByOrderId(Orders orders);

    @Query("select olo from OrderLineOrder olo where olo.id.orders.status = 'UNPROCESSED'")
    List<OrderLineOrder> getUnprocessedOrders();
}
