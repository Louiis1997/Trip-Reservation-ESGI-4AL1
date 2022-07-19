package com.esgi.mailing.queuing;

import com.esgi.mailing.MailingConstants;
import com.esgi.mailing.queuing.messages.ContractStatusUpdatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ContractStatusUpdatedConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractStatusUpdatedConsumerConfig.class);

    @KafkaListener(topics = MailingConstants.MAILING_CONTRACT_STATUS_UPDATED_TOPIC,
            groupId = MailingConstants.GROUP_ID)
    public void consumeContractStatusUpdated(String message) {
        try {
            ContractStatusUpdatedEvent contractStatusUpdatedEvent = ContractStatusUpdatedEvent.fromJSON(message);
            System.out.println("Send contract status updated mail to -> " + contractStatusUpdatedEvent.email);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.format("Error while parsing message (Topic : %s) -> %s", MailingConstants.MAILING_GENERATED_MONTHLY_BILL_TOPIC_NAME, message));
            e.printStackTrace();
        }
    }
}
