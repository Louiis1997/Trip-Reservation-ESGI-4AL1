package com.esgi.application.contract;

import com.esgi.BillingConstants;
import com.esgi.domain.models.Contract;
import com.esgi.domain.models.Subscriber;
import com.esgi.domain.repositories.ContractRepository;
import com.esgi.domain.repositories.SubscriberRepository;
import com.esgi.kernel.command.CommandHandler;
import com.esgi.model.BusinessContract;
import com.esgi.queuing.messages.CreatedContractEvent;
import com.esgi.queuing.messages.SubscribedContractEvent;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.Objects;

public class CreateContractCommandHandler implements CommandHandler<CreateContract, Contract> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ContractRepository contractRepository;
    private final SubscriberRepository subscriberRepository;

    public CreateContractCommandHandler(KafkaTemplate<String, String> kafkaTemplate, ContractRepository contractRepository, SubscriberRepository subscriberRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.contractRepository = contractRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Contract handle(CreateContract command) {
        try {
            var contractId = Integer.parseInt(contractRepository.nextId());
            var contract = contractRepository.createContract(new Contract(
                    contractId,
                    command.contractRef,
                    LocalDate.now(),
                    BusinessContract.StatusEnum.CREATED,
                    new Subscriber(Integer.parseInt(command.contractRef), command.subscriber.getSubscriberType(), contractId, command.subscriber.getContactPerson().getMail())
            ));

            var subscriber = subscriberRepository.createSubscriber(new Subscriber(
                    Integer.parseInt(subscriberRepository.nextId()),
                    Objects.requireNonNull(command.subscriber).getSubscriberType(),
                    contract.getContractId(),
                    command.subscriber.getContactPerson().getMail()
            ));

            CreatedContractEvent createdContractEvent = new CreatedContractEvent(
                    subscriber.getEmail()
            );

            SubscribedContractEvent subscribedContractEvent = new SubscribedContractEvent(
                    subscriber.getEmail()
            );

            kafkaTemplate.send(BillingConstants.MAILING_CREATED_CONTRACT_TOPIC_NAME, createdContractEvent.toJSON());
            kafkaTemplate.send(BillingConstants.MAILING_SUBSCRIBED_CONTRACT_TOPIC_NAME, subscribedContractEvent.toJSON());

            return contract;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
