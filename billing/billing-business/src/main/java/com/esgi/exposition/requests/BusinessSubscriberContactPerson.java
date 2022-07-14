package com.esgi.exposition.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * BusinessSubscriberContactPerson
 */

@JsonTypeName("BusinessSubscriber_contactPerson")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-13T15:25:34.747681+02:00[Europe/Paris]")
public class BusinessSubscriberContactPerson {

    @JsonProperty("ccuid")
    private String ccuid;
    @JsonProperty("gender")
    private GenderEnum gender;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("contactId")
    private String contactId;

    public BusinessSubscriberContactPerson ccuid(String ccuid) {
        this.ccuid = ccuid;
        return this;
    }

    /**
     * CCUID
     *
     * @return ccuid
     */
    @NotNull
    @Schema(name = "ccuid", example = "CCU-123", description = "CCUID", required = true)
    public String getCcuid() {
        return ccuid;
    }

    public void setCcuid(String ccuid) {
        this.ccuid = ccuid;
    }

    public BusinessSubscriberContactPerson gender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Gender of the contact
     *
     * @return gender
     */

    @Schema(name = "gender", example = "M", description = "Gender of the contact", required = false)
    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public BusinessSubscriberContactPerson firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * First name of the contact
     *
     * @return firstName
     */

    @Schema(name = "firstName", example = "John", description = "First name of the contact", required = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public BusinessSubscriberContactPerson lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Last name of the contact
     *
     * @return lastName
     */
    @NotNull
    @Schema(name = "lastName", example = "DOE", description = "Last name of the contact", required = true)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BusinessSubscriberContactPerson mail(String mail) {
        this.mail = mail;
        return this;
    }

    /**
     * Mail address of the contact
     *
     * @return mail
     */
    @NotNull
    @Email
    @Schema(name = "mail", example = "john.doe@acme.com", description = "Mail address of the contact", required = true)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public BusinessSubscriberContactPerson phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Phone number of the contact
     *
     * @return phone
     */

    @Schema(name = "phone", example = "+33 (0)1 56 87 65 78", description = "Phone number of the contact", required = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BusinessSubscriberContactPerson contactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    /**
     * Internal contact identifier (legal represenative of entity)
     *
     * @return contactId
     */
    @NotNull
    @Schema(name = "contactId", example = "CCU-001524", description = "Internal contact identifier (legal represenative of entity)", required = true)
    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BusinessSubscriberContactPerson businessSubscriberContactPerson = (BusinessSubscriberContactPerson) o;
        return Objects.equals(this.ccuid, businessSubscriberContactPerson.ccuid) &&
                Objects.equals(this.gender, businessSubscriberContactPerson.gender) &&
                Objects.equals(this.firstName, businessSubscriberContactPerson.firstName) &&
                Objects.equals(this.lastName, businessSubscriberContactPerson.lastName) &&
                Objects.equals(this.mail, businessSubscriberContactPerson.mail) &&
                Objects.equals(this.phone, businessSubscriberContactPerson.phone) &&
                Objects.equals(this.contactId, businessSubscriberContactPerson.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ccuid, gender, firstName, lastName, mail, phone, contactId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BusinessSubscriberContactPerson {\n");
        sb.append("    ccuid: ").append(toIndentedString(ccuid)).append("\n");
        sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    contactId: ").append(toIndentedString(contactId)).append("\n");
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

    /**
     * Gender of the contact
     */
    public enum GenderEnum {
        M("M"),

        MME("MME");

        private String value;

        GenderEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static GenderEnum fromValue(String value) {
            for (GenderEnum b : GenderEnum.values()) {
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

