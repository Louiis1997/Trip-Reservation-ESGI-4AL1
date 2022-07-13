package com.esgi.queuing;

import com.esgi.BillingConstants;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaBillingProducerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaBillingProducerConfig.class);
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;
    private KafkaTemplate<String, String> kafkaTemplate;

    public Map<String, Object> producerConfig() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }

    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    public KafkaTemplate<String, String> kafkaTemplate() {
        if (kafkaTemplate == null) {
            kafkaTemplate = new KafkaTemplate<>(producerFactory());
        }
        return kafkaTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent (Topic : %s) -> %s", BillingConstants.BILLING_SUBSCRIBE_CONTRACT_TOPIC_NAME, message));
        this.kafkaTemplate().send(BillingConstants.CONTRACT_TOPIC_NAME, message);
    }
}