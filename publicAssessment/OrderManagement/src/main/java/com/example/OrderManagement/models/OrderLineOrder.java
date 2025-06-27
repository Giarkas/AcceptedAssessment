package com.example.OrderManagement.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class OrderLineOrder {

    @EmbeddedId
    private OrderLineOrderId id;

    public OrderLineOrderId getId() {
        return id;
    }

    public void setId(OrderLineOrderId id) {
        this.id = id;
    }

    public static class Builder {
        private OrderLineOrderId id;

        public Builder id(OrderLineOrderId id) {
            this.id = id;
            return this;
        }
        public OrderLineOrder build() {
            OrderLineOrder orderLineOrder = new OrderLineOrder();
            orderLineOrder.setId(id);
            return orderLineOrder;
        }
    }
}
