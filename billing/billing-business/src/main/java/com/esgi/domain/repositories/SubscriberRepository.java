package com.esgi.domain.repositories;

import com.esgi.domain.models.Subscriber;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriberRepository {
    List<Subscriber> findAll();

    Optional<Subscriber> findById(Integer id);

    Subscriber createSubscriber(Subscriber subscriber);

    boolean doesSubscriberAlreadyExist(Subscriber project);

    Subscriber updateSubscriberStatus(Subscriber project);

    void register(Subscriber project);

    List<Subscriber> getAllActive();

    default String nextId() {
        return UUID.randomUUID().toString();
    }
}
