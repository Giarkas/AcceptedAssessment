package com.example.OrderManagement.repositories;

import com.example.OrderManagement.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderDAO extends JpaRepository<Orders, Long> {

    @Modifying
    @Query("update Orders o set o.status = 'PROCESSED' where o.orderId = ?1")
    void updateToProcessed(long orderId);
}
