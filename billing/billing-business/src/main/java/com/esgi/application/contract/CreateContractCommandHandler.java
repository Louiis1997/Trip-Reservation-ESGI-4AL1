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

    private final ContractRepository contractRepository;
    private final SubscriberRepository subscriberRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CreateContractCommandHandler(ContractRepository contractRepository, SubscriberRepository subscriberRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.contractRepository = contractRepository;
        this.subscriberRepository = subscriberRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Contract handle(CreateContract command) {
        try {
            var contract = contractRepository.createContract(new Contract(
                    Integer.parseInt(contractRepository.nextId()),
                    LocalDate.now(),
                    BusinessContract.StatusEnum.CREATED
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

            kafkaTemplate.send(BillingConstants.MAILING_CREATED_CONTRACT_TOPIC, createdContractEvent.toJSON());
            kafkaTemplate.send(BillingConstants.MAILING_SUBSCRIBED_CONTRACT_TOPIC_NAME, subscribedContractEvent.toJSON());

            return contract;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
