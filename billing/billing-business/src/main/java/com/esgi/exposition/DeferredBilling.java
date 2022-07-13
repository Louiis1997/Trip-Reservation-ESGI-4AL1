package com.esgi.exposition;

import com.esgi.model.BusinessPayment;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Describe billing contract
 */

@Schema(name = "DeferredBilling", description = "Describe billing contract")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-13T15:25:34.747681+02:00[Europe/Paris]")
public class DeferredBilling {

    @JsonProperty("creditLimit")
    private Integer creditLimit;

    @JsonProperty("payment")
    private BusinessPayment payment;

    public DeferredBilling creditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }

    /**
     * Get creditLimit
     *
     * @return creditLimit
     */

    @Schema(name = "creditLimit", example = "3000", required = false)
    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public DeferredBilling payment(BusinessPayment payment) {
        this.payment = payment;
        return this;
    }

    /**
     * Get payment
     *
     * @return payment
     */
    @Valid
    @Schema(name = "payment", required = false)
    public BusinessPayment getPayment() {
        return payment;
    }

    public void setPayment(BusinessPayment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeferredBilling deferredBilling = (DeferredBilling) o;
        return Objects.equals(this.creditLimit, deferredBilling.creditLimit) &&
                Objects.equals(this.payment, deferredBilling.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditLimit, payment);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeferredBilling {\n");
        sb.append("    creditLimit: ").append(toIndentedString(creditLimit)).append("\n");
        sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
        sb.append("}");
        return sb.toString();
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
}

