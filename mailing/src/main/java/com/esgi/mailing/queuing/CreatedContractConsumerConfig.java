package com.esgi.mailing.queuing;

import com.esgi.mailing.MailingConstants;
import com.esgi.mailing.queuing.messages.ContractStatusUpdatedEvent;
import com.esgi.mailing.queuing.messages.CreatedContractEvent;
import com.esgi.mailing.queuing.messages.SubscribedContractEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreatedContractConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatedContractConsumerConfig.class);

    @KafkaListener(topics = MailingConstants.MAILING_CREATED_CONTRACT_TOPIC,
            groupId = MailingConstants.GROUP_ID)
    public void consumeCreatedContract(String message) {
        try {
            CreatedContractEvent createdContractEvent = CreatedContractEvent.fromJSON(message);
            System.out.println("Send created contract mail to -> " + createdContractEvent.email);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.format("Error while parsing message (Topic : %s) -> %s", MailingConstants.MAILING_CREATED_CONTRACT_TOPIC, message));
            e.printStackTrace();
        }
    }
}
