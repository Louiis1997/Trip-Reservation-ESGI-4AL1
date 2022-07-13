package com.esgi.Queuing;

import com.esgi.ContractConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaContractTopicConfig {

    @Bean
    public NewTopic ContractTopic() {
        return TopicBuilder.name(ContractConstants.CONTRACT_TOPIC_NAME).partitions(1).build();
    }
}