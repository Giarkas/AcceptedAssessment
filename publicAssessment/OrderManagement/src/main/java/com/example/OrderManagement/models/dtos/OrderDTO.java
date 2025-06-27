package com.example.OrderManagement.models.dtos;

import com.example.OrderManagement.models.Orders;
import com.example.OrderManagement.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    @JsonProperty
    private long orderId;
    @JsonProperty
    private String customerName;
    @JsonProperty
    private Status status;
    @JsonProperty
    private Date orderDate;
    private List<OrderLineDTO> orderLines;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDTO> orderLines) {
        this.orderLines = orderLines;
    }

    public OrderDTO fillEntity(Orders entity) {
        this.setOrderId(entity.getOrderId());
        this.setCustomerName(entity.getCustomerName());
        this.setStatus(entity.getStatus());
        this.setOrderDate(entity.getOrderDate());
        return this;
    }
}
