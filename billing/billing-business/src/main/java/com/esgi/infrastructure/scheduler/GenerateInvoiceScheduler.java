package com.esgi.infrastructure.scheduler;

import com.esgi.application.billing.GenerateMonthlyBills;
import com.esgi.application.billing.GenerateMonthlyBillsCommandHandler;
import org.springframework.scheduling.annotation.Scheduled;

public class GenerateInvoiceScheduler {

    private final GenerateMonthlyBillsCommandHandler generateMonthlyBillsCommandHandler;

    public GenerateInvoiceScheduler(GenerateMonthlyBillsCommandHandler generateMonthlyBillsCommandHandler) {
        this.generateMonthlyBillsCommandHandler = generateMonthlyBillsCommandHandler;
    }

    // Schedule task every end of month to generate billing
    @Scheduled(cron = "0 0 0 1 * *")
    public void generateInvoice() {
        generateMonthlyBillsCommandHandler.handle(new GenerateMonthlyBills());
    }
}
