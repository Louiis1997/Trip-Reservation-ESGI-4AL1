package com.esgi.domain.repositories;

import com.esgi.domain.models.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractRepository {
    List<Contract> findAll();

    Optional<Contract> findById(String id);

    void createContract(Contract contract);

    boolean doesProjectAlreadyExist(Contract project);

    Contract updateProjectStatus(Contract project);

    void register(Contract project);
}
