package com.esgi.domain.models;

import com.esgi.model.BusinessSubscriber;

public class Subscriber {
    private final String subscriberRef;
    private final BusinessSubscriber.SubscriberTypeEnum subscriberType;

    public Subscriber(String subscriberRef, BusinessSubscriber.SubscriberTypeEnum subscriberType) {
        this.subscriberRef = subscriberRef;
        this.subscriberType = subscriberType;
    }
}
