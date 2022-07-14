package com.esgi.infrastructure.repositories.contract;

import com.esgi.domain.models.Contract;
import com.esgi.domain.repositories.ContractRepository;
import com.esgi.infrastructure.errors.contract.ContractAlreadyExistException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryContractRepository implements ContractRepository {

    private final AtomicInteger counter = new AtomicInteger(1);
    private final Map<Integer, Contract> data = new ConcurrentHashMap<>();

    public InMemoryContractRepository() {
    }

    @Override
    public List<Contract> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<Contract> findById(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public boolean doesContractAlreadyExist(Contract contract) {
        Optional<Contract> contractAlreadyExists = data.values().stream().filter((currentContract) -> Objects.equals(currentContract.getContractId(), contract.getContractId())).findAny();
        if (contractAlreadyExists.isPresent()) {
            throw new ContractAlreadyExistException("Contract with id : '" + contract.getContractId() + "' already exist");
        }
        return false;
    }

    @Override
    public Contract createContract(Contract contract) {
        doesContractAlreadyExist(contract);
        System.out.println("============================================");
        System.out.println("New contract : " + contract);
        System.out.println("============================================\n");
        register(contract);
        return contract;
    }

    @Override
    public Contract updateContractStatus(Contract contract) {
        register(contract);
        return contract;
    }

    @Override
    public void register(Contract contract) {
        data.put(contract.getContractId(), contract);
    }

    @Override
    public String nextId() {
        return String.valueOf(counter.incrementAndGet());
    }
}
