package com.esgi.mailing.queuing;

import com.esgi.mailing.MailingConstants;
import com.esgi.mailing.queuing.messages.SubscribedContractEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class GeneratedMonthlyBillConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratedMonthlyBillConsumerConfig.class);

    @KafkaListener(topics = MailingConstants.MAILING_GENERATED_MONTHLY_BILL_TOPIC_NAME,
            groupId = MailingConstants.GROUP_ID)
    public void consumeGeneratedMonthlyBill(String message) {
        try {
            SubscribedContractEvent subscribedContractEvent = SubscribedContractEvent.fromJSON(message);
            System.out.println("Send generated monthly bill mail to -> " + subscribedContractEvent.email);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.format("Error while parsing message (Topic : %s) -> %s", MailingConstants.MAILING_GENERATED_MONTHLY_BILL_TOPIC_NAME, message));
            e.printStackTrace();
        }
    }
}
