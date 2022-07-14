package com.esgi.queuing.messages;

import com.esgi.exposition.requests.BusinessDistributor;
import com.esgi.exposition.requests.BusinessSubscriber;
import com.esgi.exposition.requests.ContractRequest;
import com.esgi.exposition.requests.DeferredBilling;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    public CreateContractEvent() {
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

    public static CreateContractEvent fromJSON(String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return om.readValue(json, CreateContractEvent.class);
    }

    public BusinessSubscriber getSubscriber() {
        return subscriber;
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
