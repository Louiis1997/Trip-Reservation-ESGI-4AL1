package com.esgi.queuing;

import com.esgi.BillingConstants;
import com.esgi.application.contract.UpdateContractStatus;
import com.esgi.application.contract.UpdateContractStatusCommandHandler;
import com.esgi.queuing.messages.UpdateContractStatusEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreateContractConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateContractConsumerConfig.class);
    private final UpdateContractStatusCommandHandler updateContractStatusCommandHandler;

    public CreateContractConsumerConfig(UpdateContractStatusCommandHandler updateContractStatusCommandHandler) {
        this.updateContractStatusCommandHandler = updateContractStatusCommandHandler;
    }

    // Listen on ContractConstants.CREATED_CONTRACT_TOPIC_NAME
    @KafkaListener(topics = BillingConstants.UPDATE_CONTRACT_STATUS_TOPIC_NAME,
            groupId = BillingConstants.GROUP_ID)
    public void consumeUpdateContractStatus(String message) {
        try {
            UpdateContractStatusEvent updateContractStatusEvent = UpdateContractStatusEvent.fromJSON(message);
            LOGGER.info(String.format("Message received (Topic : %s) -> %s", BillingConstants.UPDATE_CONTRACT_STATUS_TOPIC_NAME, updateContractStatusEvent.toJSON()));

            UpdateContractStatus command = new UpdateContractStatus(updateContractStatusEvent.getContractRef(), updateContractStatusEvent.getAction());
            updateContractStatusCommandHandler.handle(command);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.format("Error while parsing message (Topic : %s) -> %s", BillingConstants.UPDATE_CONTRACT_STATUS_TOPIC_NAME, message));
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
