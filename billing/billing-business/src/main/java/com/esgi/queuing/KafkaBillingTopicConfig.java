package com.esgi.queuing;

import com.esgi.BillingConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaBillingTopicConfig {

    @Bean
    public NewTopic BillingSubscribeTopic() {
        return TopicBuilder.name(BillingConstants.BILLING_SUBSCRIBE_CONTRACT_TOPIC_NAME).partitions(1).build();
    }
}