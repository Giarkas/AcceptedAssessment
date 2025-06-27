package com.example.OrderManagement.models.dtos;

import com.example.OrderManagement.models.OrderLine;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderLineDTO {

    @JsonProperty
    private long productId;
    @JsonProperty
    private int quantity;
    @JsonProperty
    private double price;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderLineDTO fillEntity(OrderLine orderLine) {
        this.setProductId(orderLine.getProductId());
        this.setQuantity(orderLine.getQuantity());
        this.setPrice(orderLine.getPrice());
        return this;
    }
}
