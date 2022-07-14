package com.esgi.exposition.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class ContractRequest {

    @JsonProperty("productRef")
    private ProductRefEnum productRef;
    @JsonProperty("contractType")
    private ContractTypeEnum contractType;
    @JsonProperty("createdAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createdAt;
    @JsonProperty("signedAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate signedAt;
    @JsonProperty("activatedAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate activatedAt;
    @JsonProperty("expireAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expireAt;
    @JsonProperty("status")
    private StatusEnum status;
    @JsonProperty("distributor")
    private BusinessDistributor distributor;
    @JsonProperty("subscriber")
    private BusinessSubscriber subscriber;
    @JsonProperty("custom")
    private DeferredBilling custom;

    public ContractRequest productRef(ProductRefEnum productRef) {
        this.productRef = productRef;
        return this;
    }

    /**
     * External reference of the product
     *
     * @return productRef
     */

    @Schema(name = "productRef", example = "DEFERRED_BILLING", description = "External reference of the product")
    public ProductRefEnum getProductRef() {
        return productRef;
    }

    public void setProductRef(ProductRefEnum productRef) {
        this.productRef = productRef;
    }

    public ContractRequest contractType(ContractTypeEnum contractType) {
        this.contractType = contractType;
        return this;
    }

    /**
     * Type of contract
     *
     * @return contractType
     */
    @NotNull
    @Schema(name = "contractType", example = "DEFERRED_BILLING", description = "Type of contract", required = true)
    public ContractTypeEnum getContractType() {
        return contractType;
    }

    public void setContractType(ContractTypeEnum contractType) {
        this.contractType = contractType;
    }

    public ContractRequest createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Creation date of contract (use ISO 8601 format YYYY-MM-DD)
     *
     * @return createdAt
     */
    @NotNull
    @Valid
    @Schema(name = "createdAt", example = "Fri Apr 22 02:00:00 CEST 2022", description = "Creation date of contract (use ISO 8601 format YYYY-MM-DD)", required = true)
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public ContractRequest signedAt(LocalDate signedAt) {
        this.signedAt = signedAt;
        return this;
    }

    /**
     * Signed date of contract (use ISO 8601 format YYYY-MM-DD)
     *
     * @return signedAt
     */
    @Valid
    @Schema(name = "signedAt", example = "Fri Apr 22 02:00:00 CEST 2022", description = "Signed date of contract (use ISO 8601 format YYYY-MM-DD)")
    public LocalDate getSignedAt() {
        return signedAt;
    }

    public void setSignedAt(LocalDate signedAt) {
        this.signedAt = signedAt;
    }

    public ContractRequest activatedAt(LocalDate activatedAt) {
        this.activatedAt = activatedAt;
        return this;
    }

    /**
     * Activation date of contract (use ISO 8601 format YYYY-MM-DD)
     *
     * @return activatedAt
     */
    @NotNull
    @Valid
    @Schema(name = "activatedAt", example = "Sun Apr 24 02:00:00 CEST 2022", description = "Activation date of contract (use ISO 8601 format YYYY-MM-DD)", required = true)
    public LocalDate getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(LocalDate activatedAt) {
        this.activatedAt = activatedAt;
    }

    public ContractRequest expireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
        return this;
    }

    /**
     * Expiration date of contract (use ISO 8601 format YYYY-MM-DD)
     *
     * @return expireAt
     */
    @NotNull
    @Valid
    @Schema(name = "expireAt", example = "Sat Apr 01 02:00:00 CEST 2023", description = "Expiration date of contract (use ISO 8601 format YYYY-MM-DD)", required = true)
    public LocalDate getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
    }

    public ContractRequest status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Status of contact
     *
     * @return status
     */
    @NotNull
    @Schema(name = "status", example = "ACTIVE", description = "Status of contact", required = true)
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public ContractRequest distributor(BusinessDistributor distributor) {
        this.distributor = distributor;
        return this;
    }

    /**
     * Get distributor
     *
     * @return distributor
     */
    @NotNull
    @Valid
    @Schema(name = "distributor", required = true)
    public BusinessDistributor getDistributor() {
        return distributor;
    }

    public void setDistributor(BusinessDistributor distributor) {
        this.distributor = distributor;
    }

    public ContractRequest subscriber(BusinessSubscriber subscriber) {
        this.subscriber = subscriber;
        return this;
    }

    /**
     * Get subscriber
     *
     * @return subscriber
     */
    @NotNull
    @Valid
    @Schema(name = "subscriber", required = true)
    public BusinessSubscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(BusinessSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public ContractRequest custom(DeferredBilling custom) {
        this.custom = custom;
        return this;
    }

    /**
     * Get custom
     *
     * @return custom
     */
    @Valid
    @Schema(name = "custom")
    public DeferredBilling getCustom() {
        return custom;
    }

    public void setCustom(DeferredBilling custom) {
        this.custom = custom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContractRequest contractRequest = (ContractRequest) o;
        return Objects.equals(this.productRef, contractRequest.productRef) &&
                Objects.equals(this.contractType, contractRequest.contractType) &&
                Objects.equals(this.createdAt, contractRequest.createdAt) &&
                Objects.equals(this.signedAt, contractRequest.signedAt) &&
                Objects.equals(this.activatedAt, contractRequest.activatedAt) &&
                Objects.equals(this.expireAt, contractRequest.expireAt) &&
                Objects.equals(this.status, contractRequest.status) &&
                Objects.equals(this.distributor, contractRequest.distributor) &&
                Objects.equals(this.subscriber, contractRequest.subscriber) &&
                Objects.equals(this.custom, contractRequest.custom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productRef, contractType, createdAt, signedAt, activatedAt, expireAt, status, distributor, subscriber, custom);
    }

    @Override
    public String toString() {
        String sb = "class ContractRequest {\n" +
                "    productRef: " + toIndentedString(productRef) + "\n" +
                "    contractType: " + toIndentedString(contractType) + "\n" +
                "    createdAt: " + toIndentedString(createdAt) + "\n" +
                "    signedAt: " + toIndentedString(signedAt) + "\n" +
                "    activatedAt: " + toIndentedString(activatedAt) + "\n" +
                "    expireAt: " + toIndentedString(expireAt) + "\n" +
                "    status: " + toIndentedString(status) + "\n" +
                "    distributor: " + toIndentedString(distributor) + "\n" +
                "    subscriber: " + toIndentedString(subscriber) + "\n" +
                "    custom: " + toIndentedString(custom) + "\n" +
                "}";
        return sb;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    /**
     * External reference of the product
     */
    public enum ProductRefEnum {
        DEFERRED_BILLING("DEFERRED_BILLING");

        private final String value;

        ProductRefEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static ProductRefEnum fromValue(String value) {
            for (ProductRefEnum b : ProductRefEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /**
     * Type of contract
     */
    public enum ContractTypeEnum {
        DEFERRED_BILLING("DEFERRED_BILLING");

        private final String value;

        ContractTypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static ContractTypeEnum fromValue(String value) {
            for (ContractTypeEnum b : ContractTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /**
     * Status of contact
     */
    public enum StatusEnum {
        CREATED("CREATED"),

        ACTIVE("ACTIVE"),

        SUSPENDED("SUSPENDED"),

        EXPIRED("EXPIRED");

        private final String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static StatusEnum fromValue(String value) {
            for (StatusEnum b : StatusEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}