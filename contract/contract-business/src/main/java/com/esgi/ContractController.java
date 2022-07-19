package com.esgi;

import com.esgi.api.ContractsApiDelegate;
import com.esgi.messages.CreateContractEvent;
import com.esgi.messages.UpdateContractStatusEvent;
import com.esgi.model.ContractActionRequest;
import com.esgi.model.ContractRequest;
import com.esgi.model.ContractResponse;
import com.esgi.queuing.KafkaContractProducerConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ContractController implements ContractsApiDelegate {

    private final KafkaContractProducerConfig kafkaSender;

    public ContractController(KafkaContractProducerConfig kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @Override
    public ResponseEntity<ContractResponse> postContract(ContractRequest contractRequest) {
        CreateContractEvent event = new CreateContractEvent(contractRequest.getProductRef(), contractRequest.getContractType(), contractRequest.getCreatedAt(), contractRequest.getSignedAt(), contractRequest.getActivatedAt(), contractRequest.getExpireAt(), contractRequest.getStatus(), contractRequest.getDistributor(), contractRequest.getSubscriber(), contractRequest.getCustom());

        try {
            kafkaSender.send(ContractConstants.CREATED_CONTRACT_TOPIC_NAME, event.toJSON());
            return ResponseEntity.created(URI.create("")).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // with body
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchContract(String contractRef, ContractActionRequest contractActionRequest) {

        UpdateContractStatusEvent event = new UpdateContractStatusEvent(contractActionRequest.getAction(), contractRef);

        try {
            kafkaSender.send(ContractConstants.CONTRACT_STATUS_UPDATED_TOPIC, event.toJSON());
            return ResponseEntity.ok(null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}