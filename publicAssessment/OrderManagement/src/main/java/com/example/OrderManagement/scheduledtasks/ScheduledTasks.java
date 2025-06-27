package com.example.OrderManagement.scheduledtasks;

import com.example.OrderManagement.services.OrderService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static OrderService orderService;

    ScheduledTasks(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void updateOrdersScheduler(){

        log.info("Updating Orders to Processed!");
        orderService.processOrders();
    }
}
