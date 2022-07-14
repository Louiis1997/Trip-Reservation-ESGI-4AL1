package com.esgi.queuing;

import com.esgi.application.billing.GenerateMonthlyBillsCommandHandler;
import com.esgi.application.contract.CreateContractCommandHandler;
import com.esgi.domain.repositories.BillRepository;
import com.esgi.domain.repositories.ContractRepository;
import com.esgi.domain.repositories.SubscriberRepository;
import com.esgi.infrastructure.repositories.bill.InMemoryBillRepository;
import com.esgi.infrastructure.repositories.contract.InMemoryContractRepository;
import com.esgi.infrastructure.repositories.subscriber.InMemorySubscriberRepository;
import com.esgi.infrastructure.scheduler.GenerateInvoiceScheduler;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableScheduling
public class KafkaBillingProducerConfig {

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

    @Bean
    ContractRepository contractRepositories() {
        return new InMemoryContractRepository();
    }

    @Bean
    SubscriberRepository subscriberRepository() {
        return new InMemorySubscriberRepository();
    }

    BillRepository billRepository() {
        return new InMemoryBillRepository();
    }

    @Bean
    public CreateContractCommandHandler createContractCommandHandler() {
        return new CreateContractCommandHandler(kafkaTemplate(), contractRepositories(), subscriberRepository());
    }

    @Bean
    public GenerateMonthlyBillsCommandHandler generateMonthlyBillsCommandHandler() {
        return new GenerateMonthlyBillsCommandHandler(kafkaTemplate(), billRepository(), subscriberRepository());
    }

    @Bean
    public GenerateInvoiceScheduler generateInvoiceScheduler() {
        return new GenerateInvoiceScheduler(generateMonthlyBillsCommandHandler());
    }
}