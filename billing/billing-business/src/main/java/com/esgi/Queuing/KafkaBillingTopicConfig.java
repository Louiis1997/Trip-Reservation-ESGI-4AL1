package com.esgi.Queuing;

import com.esgi.BillingConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaBillingTopicConfig {

    @Bean
    public NewTopic BillingTopic() {
        return TopicBuilder.name(BillingConstants.BILLING_TOPIC_NAME).partitions(1).build();
    }
}