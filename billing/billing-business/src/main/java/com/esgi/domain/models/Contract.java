package com.esgi.domain.models;

import com.esgi.model.BusinessContract;

import java.time.LocalDate;

public class Contract {
    private final Integer contractId;
    private final String ref;
    private final LocalDate createdAt;
    private final Subscriber subscriber;
    private BusinessContract.StatusEnum status;

    public Contract(Integer contractId, String ref, LocalDate createdAt, BusinessContract.StatusEnum status, Subscriber subscriber) {
        this.contractId = contractId;
        this.ref = ref;
        this.createdAt = createdAt;
        this.status = status;
        this.subscriber = subscriber;
    }

    public String getRef() {
        return ref;
    }

    public Integer getContractId() {
        return contractId;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setStatus(BusinessContract.StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", ref='" + ref + '\'' +
                ", createdAt=" + createdAt +
                ", subscriber=" + subscriber +
                ", status=" + status +
                '}';
    }
}
