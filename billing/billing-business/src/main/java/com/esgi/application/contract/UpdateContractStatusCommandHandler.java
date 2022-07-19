package com.esgi.application.contract;

import com.esgi.BillingConstants;
import com.esgi.domain.models.Contract;
import com.esgi.domain.repositories.ContractRepository;
import com.esgi.kernel.command.CommandHandler;
import com.esgi.queuing.messages.ContractStatusUpdatedEvent;
import org.springframework.kafka.core.KafkaTemplate;

public class UpdateContractStatusCommandHandler implements CommandHandler<UpdateContractStatus, Contract> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ContractRepository contractRepository;

    public UpdateContractStatusCommandHandler(KafkaTemplate<String, String> kafkaTemplate, ContractRepository contractRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract handle(UpdateContractStatus command) {
        try {
            var contract = contractRepository.findByRef(command.contractRef);
            if (contract.isPresent()) {
                var contractToUpdate = contract.get();
                contractToUpdate.setStatus(command.status);
                var updatedContract = contractRepository.updateContractStatus(contractToUpdate);
                System.out.println("Updated contract : " + updatedContract);
                kafkaTemplate.send(BillingConstants.CONTRACT_STATUS_UPDATED_TOPIC, new ContractStatusUpdatedEvent(contractToUpdate.getSubscriber().getEmail()).toJSON());
                return contractToUpdate;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
