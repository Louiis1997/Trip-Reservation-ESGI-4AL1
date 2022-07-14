package com.esgi.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Bill {
    public final List<Operation> operations;
    public final LocalDate createdAt;
    private final Integer id;
    private final Integer customerId;
    private final Integer contractId;
    private final BigDecimal billAmount;

    public Bill(Integer id, Integer customerId, Integer contractId, BigDecimal billAmount, List<Operation> operations, LocalDate createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.contractId = contractId;
        this.billAmount = billAmount;
        this.operations = operations;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "operations=" + operations +
                ", createdAt=" + createdAt +
                ", id=" + id +
                ", customerId=" + customerId +
                ", contractId=" + contractId +
                ", billAmount=" + billAmount +
                '}';
    }
}
