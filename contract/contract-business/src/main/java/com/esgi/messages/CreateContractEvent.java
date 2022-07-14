package com.esgi.messages;

import com.esgi.model.BusinessDistributor;
import com.esgi.model.BusinessSubscriber;
import com.esgi.model.ContractRequest;
import com.esgi.model.DeferredBilling;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CreateContractEvent {
    @JsonProperty("productRef")
    public final ContractRequest.ProductRefEnum productRef;
    @JsonProperty("contractType")
    public final ContractRequest.ContractTypeEnum contractType;
    @JsonProperty("createdAt")
    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE
    )
    public final LocalDate createdAt;
    @JsonProperty("signedAt")
    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE
    )
    public final LocalDate signedAt;
    @JsonProperty("activatedAt")
    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE
    )
    public final LocalDate activatedAt;
    @JsonProperty("expiredAt")
    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE
    )
    public final LocalDate expireAt;
    @JsonProperty("status")
    public final ContractRequest.StatusEnum status;
    @JsonProperty("distributor")
    public final BusinessDistributor distributor;
    @JsonProperty("subscriber")
    public final BusinessSubscriber subscriber;
    @JsonProperty("custom")
    public final DeferredBilling custom;

    @JsonCreator()
    public CreateContractEvent(ContractRequest.ProductRefEnum productRef, ContractRequest.ContractTypeEnum contractType, LocalDate createdAt, LocalDate signedAt, LocalDate activatedAt, LocalDate expireAt, ContractRequest.StatusEnum status, BusinessDistributor distributor, BusinessSubscriber subscriber, DeferredBilling custom) {
        this.productRef = productRef;
        this.contractType = contractType;
        this.createdAt = createdAt;
        this.signedAt = signedAt;
        this.activatedAt = activatedAt;
        this.expireAt = expireAt;
        this.status = status;
        this.distributor = distributor;
        this.subscriber = subscriber;
        this.custom = custom;
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(this);
    }

    public ContractRequest toModel() {
        return new ContractRequest()
                .contractType(this.contractType)
                .productRef(this.productRef)
                .createdAt(this.createdAt)
                .signedAt(this.signedAt)
                .activatedAt(this.activatedAt)
                .expireAt(this.expireAt)
                .status(this.status)
                .distributor(this.distributor)
                .subscriber(this.subscriber)
                .custom(this.custom);
    }
}
