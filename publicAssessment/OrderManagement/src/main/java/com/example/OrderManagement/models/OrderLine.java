package com.example.OrderManagement.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderLine {
    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty
    private long productId;
    @JsonProperty
    private int quantity;
    @JsonProperty
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public static class Builder{
        private long productId;
        private int quantity;
        private double price;

        public Builder productId(long productId) {
            this.productId = productId;
            return this;
        }
        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }
        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public OrderLine build(){
            OrderLine orderLine = new OrderLine();
            orderLine.setProductId(productId);
            orderLine.setQuantity(quantity);
            orderLine.setPrice(price);
            return orderLine;
        }
    }
}
