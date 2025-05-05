package com.example.demoapplication.model;

import java.util.List;

public class ProductResponse {
    private String name;
    private List<OrderInfo> orders;

    public ProductResponse(String name, List<OrderInfo> orders) {
        this.name = name;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public List<OrderInfo> getOrders() {
        return orders;
    }
}