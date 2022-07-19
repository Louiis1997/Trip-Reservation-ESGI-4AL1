package com.esgi.queuing;

import com.esgi.BillingConstants;
import com.esgi.application.contract.CreateContract;
import com.esgi.application.contract.CreateContractCommandHandler;
import com.esgi.queuing.messages.CreateContractEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UpdateContractStatusConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateContractStatusConsumerConfig.class);
    private final CreateContractCommandHandler createContractCommandHandler;

    public UpdateContractStatusConsumerConfig(CreateContractCommandHandler createContractCommandHandler) {
        this.createContractCommandHandler = createContractCommandHandler;
    }

    @KafkaListener(topics = BillingConstants.CREATE_CONTRACT_TOPIC_NAME,
            groupId = BillingConstants.GROUP_ID)
    public void consumeCreateContract(String message) {
        try {
            CreateContractEvent createContractEvent = CreateContractEvent.fromJSON(message);
            LOGGER.info(String.format("Message received (Topic : %s) -> %s", BillingConstants.CREATE_CONTRACT_TOPIC_NAME, createContractEvent.toJSON()));

            CreateContract command = new CreateContract(createContractEvent.getSubscriber(), String.valueOf(Objects.requireNonNull(createContractEvent.createdAt).hashCode()), createContractEvent.createdAt, createContractEvent.expireAt);
            createContractCommandHandler.handle(command);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.format("Error while parsing message (Topic : %s) -> %s", BillingConstants.CREATE_CONTRACT_TOPIC_NAME, message));
            e.printStackTrace();
        }
    }

}
