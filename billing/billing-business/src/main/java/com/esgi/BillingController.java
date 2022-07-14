package com.esgi;

import com.esgi.queuing.KafkaBillingProducerConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/billing/")
public class BillingController {

    private final KafkaBillingProducerConfig kafkaSender;

    public BillingController(KafkaBillingProducerConfig kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

}