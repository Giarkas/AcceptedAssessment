package com.example.LoggingService.docs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("orders")
public class Orders {
    @Id
    private long orderId;
    private double amount;
    private Date orderDate;
    private int itemsCount;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public static class Builder {
        private double amount;
        private Date orderDate;
        private int itemsCount;
        private long orderId;

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }
        public Builder orderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }
        public Builder itemsCount(int itemsCount) {
            this.itemsCount = itemsCount;
            return this;
        }
        public Builder orderId(long orderId) {
            this.orderId = orderId;
            return this;
        }
        public Orders build() {
            Orders orders = new Orders();
            orders.setOrderId(orderId);
            orders.setAmount(amount);
            orders.setOrderDate(orderDate);
            orders.setItemsCount(itemsCount);
            return orders;
        }
    }
}
