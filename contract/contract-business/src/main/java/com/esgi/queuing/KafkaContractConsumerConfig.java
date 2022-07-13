package com.esgi.queuing;

import com.esgi.ContractConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaContractConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaContractConsumerConfig.class);

    @KafkaListener(topics = ContractConstants.CONTRACT_TOPIC_NAME,
            groupId = ContractConstants.GROUP_ID)
    public void consume(String message) {
        LOGGER.info(String.format("Message received (Topic : %s) -> %s", ContractConstants.CONTRACT_TOPIC_NAME, message));
    }
}