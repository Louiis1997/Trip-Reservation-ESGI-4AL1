package com.esgi.domain.models;

import com.esgi.model.BusinessBuyer;

public class Buyer {
    private final String id;
    private final BusinessBuyer.BuyerTypeEnum buyerType;

    public Buyer(String id, BusinessBuyer.BuyerTypeEnum buyerType) {
        this.id = id;
        this.buyerType = buyerType;
    }
}
