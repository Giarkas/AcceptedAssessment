package com.example.LoggingService.repository;

import com.example.LoggingService.docs.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Orders, Long> {
}
