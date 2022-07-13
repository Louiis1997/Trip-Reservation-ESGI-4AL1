package com.esgi;

import com.esgi.api.ContractsApiDelegate;
import com.esgi.messages.SubscribeContractEvent;
import com.esgi.model.ContractRequest;
import com.esgi.model.ContractResponse;
import com.esgi.queuing.KafkaContractProducerConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController implements ContractsApiDelegate {

    private final KafkaContractProducerConfig kafkaSender;

    public ContractController(KafkaContractProducerConfig kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @Override
    public ResponseEntity<ContractResponse> postContract(ContractRequest contractRequest) {
        SubscribeContractEvent event = new SubscribeContractEvent(
                contractRequest.getProductRef(),
                contractRequest.getContractType(),
                contractRequest.getCreatedAt(),
                contractRequest.getSignedAt(),
                contractRequest.getActivatedAt(),
                contractRequest.getExpireAt(),
                contractRequest.getStatus(),
                contractRequest.getDistributor(),
                contractRequest.getSubscriber(),
                contractRequest.getCustom()
        );

        try {
            kafkaSender.sendSubscribeContractMessage(event.toJSON());
            return ResponseEntity.ok(new ContractResponse());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}