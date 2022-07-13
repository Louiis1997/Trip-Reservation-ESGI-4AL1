package com.esgi;

import com.esgi.queuing.KafkaBillingProducerConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/billing/")
public class BillingController {

    private final KafkaBillingProducerConfig kafkaSender;

    public BillingController(KafkaBillingProducerConfig kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        kafkaSender.sendMessage(message);
        return "Message sent to the Kafka Topic contract_topic Successfully";
    }

}