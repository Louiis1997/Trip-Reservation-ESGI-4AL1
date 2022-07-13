package com.esgi.queuing;

import com.esgi.BillingConstants;
import com.esgi.messages.SubscribeContractEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaBillingConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaBillingConsumerConfig.class);

    @KafkaListener(topics = BillingConstants.BILLING_SUBSCRIBE_CONTRACT_TOPIC_NAME,
            groupId = BillingConstants.GROUP_ID)
    public void consumeSubscribeContract(String message) {
        try {
            SubscribeContractEvent event = SubscribeContractEvent.fromJSON(message);
            LOGGER.info(String.format("Message received (Topic : %s) -> %s", BillingConstants.BILLING_SUBSCRIBE_CONTRACT_TOPIC_NAME, event.toJSON()));
        } catch (Exception e) {
            LOGGER.error(String.format("Error while parsing message (Topic : %s) -> %s", BillingConstants.BILLING_SUBSCRIBE_CONTRACT_TOPIC_NAME, message));
            LOGGER.error(e.getMessage());
        }
    }
}