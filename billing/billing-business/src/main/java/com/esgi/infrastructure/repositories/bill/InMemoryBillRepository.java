package com.esgi.infrastructure.repositories.bill;

import com.esgi.domain.models.Bill;
import com.esgi.domain.repositories.BillRepository;
import com.esgi.infrastructure.errors.bill.BillAlreadyExistException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryBillRepository implements BillRepository {

    private final AtomicInteger counter = new AtomicInteger(1);
    private final Map<Integer, Bill> data = new ConcurrentHashMap<>();

    public InMemoryBillRepository() {
    }

    @Override
    public List<Bill> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<Bill> findById(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public boolean doesBillAlreadyExist(Bill bill) {
        Optional<Bill> billAlreadyExists = data.values().stream().filter((currentBill) -> Objects.equals(currentBill.getId(), bill.getId())).findAny();
        if (billAlreadyExists.isPresent()) {
            throw new BillAlreadyExistException("Bill with id : '" + bill.getId() + "' already exist");
        }
        return false;
    }

    @Override
    public Bill createBill(Bill bill) {
        doesBillAlreadyExist(bill);
        System.out.println("============================================");
        System.out.println("New bill : " + bill);
        System.out.println("============================================\n");
        register(bill);
        return bill;
    }

    @Override
    public Bill updateBillStatus(Bill bill) {
        register(bill);
        return bill;
    }

    @Override
    public void register(Bill bill) {
        data.put(bill.getId(), bill);
    }

    @Override
    public String nextId() {
        return String.valueOf(counter.incrementAndGet());
    }
}
