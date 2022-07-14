package com.esgi.domain.models;

import com.esgi.model.BusinessContract;

import java.time.LocalDate;

public class Contract {
    private final Integer contractId;
    private final LocalDate createdAt;
    private final BusinessContract.StatusEnum status;

    public Contract(Integer contractId, LocalDate createdAt, BusinessContract.StatusEnum status) {
        this.contractId = contractId;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Integer getContractId() {
        return contractId;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}
