package com.esgi.domain.models;

import com.esgi.exposition.requests.BusinessSubscriber.SubscriberTypeEnum;

public class Subscriber {
    private final Integer subscriberId;
    private final SubscriberTypeEnum subscriberType;
    private final Integer contractId;
    private final String email;

    public Subscriber(Integer subscriberId, SubscriberTypeEnum subscriberType, Integer contractId, String email) {
        this.subscriberId = subscriberId;
        this.subscriberType = subscriberType;
        this.contractId = contractId;
        this.email = email;
    }

    public Integer getSubscriberId() {
        return subscriberId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "subscriberId=" + subscriberId +
                ", subscriberType=" + subscriberType +
                ", contractId=" + contractId +
                '}';
    }
}
