package com.example.LoggingService.controllers;

import com.example.LoggingService.docs.Orders;
import com.example.LoggingService.docs.dtos.OrderDTO;
import com.example.LoggingService.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders")
public class OrderLogController {

    private OrderService orderService;
    public OrderLogController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/logs")
    public ResponseEntity<String> request(@RequestBody OrderDTO orderDTO) {



        orderService.logOrder(orderDTO);

        return ResponseEntity.ok("");
    }
}

