package com.example.demoapplication.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderInfo {
    private String productName;
    private BigDecimal amount;
    private LocalDate date;

    public OrderInfo(String productName, BigDecimal amount, LocalDate date) {
        this.productName = productName;
        this.amount = amount;
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}