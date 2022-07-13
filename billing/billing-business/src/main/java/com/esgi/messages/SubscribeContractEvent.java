package com.esgi.messages;

import com.esgi.exposition.BusinessDistributor;
import com.esgi.exposition.BusinessSubscriber;
import com.esgi.exposition.ContractRequest;
import com.esgi.exposition.DeferredBilling;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    public SubscribeContractEvent() {
        this.productRef = null;
        this.contractType = null;
        this.createdAt = null;
        this.signedAt = null;
        this.activatedAt = null;
        this.expireAt = null;
        this.status = null;
        this.distributor = null;
        this.subscriber = null;
        this.custom = null;
    }

    public static SubscribeContractEvent fromJSON(String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return om.readValue(json, SubscribeContractEvent.class);
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(this);
    }

}
