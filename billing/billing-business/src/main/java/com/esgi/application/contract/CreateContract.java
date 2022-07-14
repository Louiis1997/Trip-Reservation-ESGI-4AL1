package com.esgi.application.contract;

import com.esgi.exposition.requests.BusinessSubscriber;
import com.esgi.kernel.command.Command;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

public class CreateContract implements Command {
    public final BusinessSubscriber subscriber;
    public final LocalDate createdAt;
    public final LocalDate expireAt;

    public CreateContract(BusinessSubscriber subscriber, LocalDate createdAt, LocalDate expireAt) {
        this.subscriber = subscriber;
        this.createdAt = createdAt;
        this.expireAt = expireAt;
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(this);
    }
}
