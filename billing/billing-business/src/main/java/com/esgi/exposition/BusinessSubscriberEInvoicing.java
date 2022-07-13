package com.esgi.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * Necessary for e-Invoicing &amp; e-Reporting
 */

@Schema(name = "BusinessSubscriber_eInvoicing", description = "Necessary for e-Invoicing & e-Reporting")
@JsonTypeName("BusinessSubscriber_eInvoicing")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-13T15:25:34.747681+02:00[Europe/Paris]")
public class BusinessSubscriberEInvoicing {

    @JsonProperty("siren")
    private String siren;

    @JsonProperty("tvaIntracommunautaire")
    private String tvaIntracommunautaire;

    @JsonProperty("country")
    private String country;

    public BusinessSubscriberEInvoicing siren(String siren) {
        this.siren = siren;
        return this;
    }

    /**
     * Get siren
     *
     * @return siren
     */

    @Schema(name = "siren", example = "784671695", required = false)
    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public BusinessSubscriberEInvoicing tvaIntracommunautaire(String tvaIntracommunautaire) {
        this.tvaIntracommunautaire = tvaIntracommunautaire;
        return this;
    }

    /**
     * Get tvaIntracommunautaire
     *
     * @return tvaIntracommunautaire
     */

    @Schema(name = "tvaIntracommunautaire", example = "FR53157896342", required = false)
    public String getTvaIntracommunautaire() {
        return tvaIntracommunautaire;
    }

    public void setTvaIntracommunautaire(String tvaIntracommunautaire) {
        this.tvaIntracommunautaire = tvaIntracommunautaire;
    }

    public BusinessSubscriberEInvoicing country(String country) {
        this.country = country;
        return this;
    }

    /**
     * Get country
     *
     * @return country
     */

    @Schema(name = "country", example = "FRANCE", required = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BusinessSubscriberEInvoicing businessSubscriberEInvoicing = (BusinessSubscriberEInvoicing) o;
        return Objects.equals(this.siren, businessSubscriberEInvoicing.siren) &&
                Objects.equals(this.tvaIntracommunautaire, businessSubscriberEInvoicing.tvaIntracommunautaire) &&
                Objects.equals(this.country, businessSubscriberEInvoicing.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siren, tvaIntracommunautaire, country);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BusinessSubscriberEInvoicing {\n");
        sb.append("    siren: ").append(toIndentedString(siren)).append("\n");
        sb.append("    tvaIntracommunautaire: ").append(toIndentedString(tvaIntracommunautaire)).append("\n");
        sb.append("    country: ").append(toIndentedString(country)).append("\n");
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

