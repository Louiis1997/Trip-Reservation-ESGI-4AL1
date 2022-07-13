package com.esgi;

import com.esgi.Queuing.KafkaContractProducerConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contract/")
public class ContractController {

    private final KafkaContractProducerConfig kafkaSender;

    public ContractController(KafkaContractProducerConfig kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        kafkaSender.sendMessage(message);
        return "Message sent to the Kafka Topic billing_topic Successfully";
    }

}