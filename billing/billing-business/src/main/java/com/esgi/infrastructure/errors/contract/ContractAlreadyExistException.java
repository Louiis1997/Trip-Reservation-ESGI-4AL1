package com.esgi.infrastructure.errors.contract;

public class ContractAlreadyExistException extends RuntimeException {

    public ContractAlreadyExistException(String message, Exception e) {
        super(message, e);
    }

    public ContractAlreadyExistException(String message) {
        super(message);
    }

}
