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

import java.time.LocalDate;

public class SubscribeContractEvent {
    @JsonProperty("productRef")
    public final ContractRequest.ProductRefEnum productRef;
    @JsonProperty("contractType")
    public final ContractRequest.ContractTypeEnum contractType;
    @JsonProperty("createdAt")
    public final LocalDate createdAt;
    @JsonProperty("signedAt")
    public final LocalDate signedAt;
    @JsonProperty("activatedAt")
    public final LocalDate activatedAt;
    @JsonProperty("expiredAt")
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
    public SubscribeContractEvent(ContractRequest.ProductRefEnum productRef, ContractRequest.ContractTypeEnum contractType, LocalDate createdAt, LocalDate signedAt, LocalDate activatedAt, LocalDate expireAt, ContractRequest.StatusEnum status, BusinessDistributor distributor, BusinessSubscriber subscriber, DeferredBilling custom) {
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
}
