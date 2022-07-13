package com.esgi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/billing/")
public class BillingController {

    private final KafkaProducerConfig kafkaSender;

    public BillingController(KafkaProducerConfig kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        kafkaSender.sendMessage(message);
        return "Message sent to the Kafka Topic billing_topic Successfully";
    }

}