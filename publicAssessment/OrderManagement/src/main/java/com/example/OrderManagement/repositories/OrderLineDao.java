package com.example.OrderManagement.repositories;

import com.example.OrderManagement.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineDao extends JpaRepository <OrderLine, Long>{

    @Modifying
    @Query("delete from OrderLine ol where ol in ?1")
    void deleteByIn(List<OrderLine> orderLines);
}
