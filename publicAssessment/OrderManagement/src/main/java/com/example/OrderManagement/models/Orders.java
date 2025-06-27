package com.example.OrderManagement.models;

import com.example.OrderManagement.models.enums.Status;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private String customerName;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date orderDate;

    public Orders() {
        this.status = Status.UNPROCESSED;
    }

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

    public static class Builder {
        private String customerName;
        private Date orderDate;

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }
        public Builder orderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }
        public Orders build() {
            Orders orders = new Orders();
            orders.setCustomerName(customerName);
            orders.setOrderDate(orderDate);
            return orders;
        }
    }
}

