package com.esgi.application.billing;

import com.esgi.BillingConstants;
import com.esgi.domain.models.Bill;
import com.esgi.domain.models.Operation;
import com.esgi.domain.models.Subscriber;
import com.esgi.domain.repositories.BillRepository;
import com.esgi.domain.repositories.SubscriberRepository;
import com.esgi.kernel.command.CommandHandler;
import com.esgi.queuing.messages.GeneratedMonthlyBillEvent;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenerateMonthlyBillsCommandHandler implements CommandHandler<GenerateMonthlyBills, List<Bill>> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final BillRepository billRepository;
    private final SubscriberRepository subscriberRepository;

    public GenerateMonthlyBillsCommandHandler(KafkaTemplate<String, String> kafkaTemplate, BillRepository billRepository, SubscriberRepository subscriberRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public List<Bill> handle(GenerateMonthlyBills command) {
        try {
            List<Bill> bills = new ArrayList<>();
            List<Subscriber> subscribers = subscriberRepository.getAllActive();
            if (subscribers.size() <= 0) {
                System.out.println("No monthly bills to generate....");
                return bills;
            }

            System.out.println("Generating monthly bills...");

            // Loop on all subscribers
            for (var subscriber : subscribers) {
                var operations = new ArrayList<Operation>();
                var contractId = subscriber.getContractId();
                var bill = billRepository.createBill(new Bill(
                        Integer.parseInt(billRepository.nextId()),
                        subscriber.getContractId(),
                        contractId,
                        BigDecimal.valueOf(0),
                        operations,
                        LocalDate.now()
                ));

                // Send bill to kafka
                GeneratedMonthlyBillEvent generatedMonthlyBillsEvent = new GeneratedMonthlyBillEvent(
                        subscriber.getEmail()
                );

                kafkaTemplate.send(BillingConstants.GENERATED_MONTHLY_BILL_TOPIC_NAME, generatedMonthlyBillsEvent.toJSON());

                System.out.println("Generated bill : " + generatedMonthlyBillsEvent.toJSON());

                bills.add(bill);
            }

            return bills;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
