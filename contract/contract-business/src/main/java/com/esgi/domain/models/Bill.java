package com.esgi.domain.models;

import java.math.BigDecimal;

public class Bill {
    private final String id;
    private final String customerId;
    private final BigDecimal billAmount;

    public Bill(String id, String customerId, BigDecimal billAmount) {
        this.id = id;
        this.customerId = customerId;
        this.billAmount = billAmount;
    }
}
