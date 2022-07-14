package com.esgi.mailing.queuing;

import com.esgi.mailing.MailingConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaMailingTopicConfig {

    @Bean
    public NewTopic MailingContractCreatedTopic() {
        return TopicBuilder.name(MailingConstants.MAILING_CREATED_CONTRACT_TOPIC).partitions(1).build();
    }

    @Bean
    public NewTopic MailingSubscribedContractTopic() {
        return TopicBuilder.name(MailingConstants.MAILING_SUBSCRIBED_CONTRACT_TOPIC_NAME).partitions(1).build();
    }
}