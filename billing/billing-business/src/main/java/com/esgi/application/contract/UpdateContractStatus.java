package com.esgi.application.contract;

import com.esgi.kernel.command.Command;
import com.esgi.model.BusinessContract;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class UpdateContractStatus implements Command {
    public final String contractRef;
    public final BusinessContract.StatusEnum status;

    public UpdateContractStatus(String contractRef, String status) {
        this.contractRef = contractRef;
        this.status = BusinessContract.StatusEnum.fromValue(status);
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(this);
    }
}
