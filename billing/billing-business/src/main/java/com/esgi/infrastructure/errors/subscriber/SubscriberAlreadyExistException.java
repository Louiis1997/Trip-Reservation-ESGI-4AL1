package com.esgi.infrastructure.errors.subscriber;

public class SubscriberAlreadyExistException extends RuntimeException {

    public SubscriberAlreadyExistException(String message, Exception e) {
        super(message, e);
    }

    public SubscriberAlreadyExistException(String message) {
        super(message);
    }

}
