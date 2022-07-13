package com.esgi.domain.models;

import com.esgi.exposition.ContractRequest;

import java.time.LocalDate;

public class Contract {
    private final String id;
    private final ContractRequest.ProductRefEnum productRef;
    private final ContractRequest.ContractTypeEnum contractType;
    private final LocalDate createdAt;
    private final LocalDate signedAt;
    private final LocalDate activatedAt;
    private final LocalDate expireAt;
    private final ContractRequest.StatusEnum status;

    public Contract(String id, ContractRequest.ProductRefEnum productRef, ContractRequest.ContractTypeEnum contractType, LocalDate createdAt, LocalDate signedAt, LocalDate activatedAt, LocalDate expireAt, ContractRequest.StatusEnum status) {
        this.id = id;
        this.productRef = productRef;
        this.contractType = contractType;
        this.createdAt = createdAt;
        this.signedAt = signedAt;
        this.activatedAt = activatedAt;
        this.expireAt = expireAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public ContractRequest.ProductRefEnum getProductRef() {
        return productRef;
    }

    public ContractRequest.ContractTypeEnum getContractType() {
        return contractType;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getSignedAt() {
        return signedAt;
    }

    public LocalDate getActivatedAt() {
        return activatedAt;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public ContractRequest.StatusEnum getStatus() {
        return status;
    }
}
