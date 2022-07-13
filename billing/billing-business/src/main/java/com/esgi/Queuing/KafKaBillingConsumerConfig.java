package com.esgi.Queuing;

import com.esgi.BillingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafKaBillingConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafKaBillingConsumerConfig.class);

    @KafkaListener(topics = BillingConstants.BILLING_TOPIC_NAME,
            groupId = BillingConstants.GROUP_ID)
    public void consume(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}