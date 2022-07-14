package com.esgi.infrastructure.errors.bill;

public class BillAlreadyExistException extends RuntimeException {

    public BillAlreadyExistException(String message, Exception e) {
        super(message, e);
    }

    public BillAlreadyExistException(String message) {
        super(message);
    }

}
