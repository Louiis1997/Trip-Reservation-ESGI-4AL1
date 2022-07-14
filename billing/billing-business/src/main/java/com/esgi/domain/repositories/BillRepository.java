package com.esgi.domain.repositories;

import com.esgi.domain.models.Bill;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BillRepository {
    List<Bill> findAll();

    Optional<Bill> findById(Integer id);

    Bill createBill(Bill bill);

    boolean doesBillAlreadyExist(Bill project);

    Bill updateBillStatus(Bill project);

    void register(Bill project);

    default String nextId() {
        return UUID.randomUUID().toString();
    }
}
