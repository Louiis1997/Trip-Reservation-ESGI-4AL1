package com.esgi.domain.repositories;

import com.esgi.domain.models.Contract;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractRepository {
    List<Contract> findAll();

    Optional<Contract> findByRef(String ref);

    Contract createContract(Contract contract);

    boolean doesContractAlreadyExist(Contract project);

    Contract updateContractStatus(Contract project);

    void register(Contract project);

    default String nextId() {
        return UUID.randomUUID().toString();
    }
}
