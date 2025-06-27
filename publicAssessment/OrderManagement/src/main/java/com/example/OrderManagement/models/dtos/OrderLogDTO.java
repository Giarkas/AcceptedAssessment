package com.example.OrderManagement.models.dtos;


import java.util.Date;

public class OrderLogDTO {

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
        private long orderId;
        private double amount;
        private Date orderDate;
        private int itemsCount;

        public Builder orderId(long orderId) {
            this.orderId = orderId;
            return this;
        }
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

        public OrderLogDTO build() {
            OrderLogDTO dto = new OrderLogDTO();
            dto.setOrderId(orderId);
            dto.setAmount(amount);
            dto.setOrderDate(orderDate);
            dto.setItemsCount(itemsCount);
            return dto;
        }
    }
}
