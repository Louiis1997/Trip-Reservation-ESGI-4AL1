package com.esgi.infrastructure.repositories.subscriber;

import com.esgi.domain.models.Subscriber;
import com.esgi.domain.repositories.SubscriberRepository;
import com.esgi.infrastructure.errors.subscriber.SubscriberAlreadyExistException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemorySubscriberRepository implements SubscriberRepository {

    private final AtomicInteger counter = new AtomicInteger(1);
    private final Map<Integer, Subscriber> data = new ConcurrentHashMap<>();

    public InMemorySubscriberRepository() {
    }

    @Override
    public List<Subscriber> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<Subscriber> findById(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public boolean doesSubscriberAlreadyExist(Subscriber subscriber) {
        Optional<Subscriber> subscriberAlreadyExists = data.values().stream().filter((currentSubscriber) -> Objects.equals(currentSubscriber.getSubscriberId(), subscriber.getSubscriberId())).findAny();
        if (subscriberAlreadyExists.isPresent()) {
            throw new SubscriberAlreadyExistException("Subscriber with id : '" + subscriber.getSubscriberId() + "' already exist");
        }
        return false;
    }

    @Override
    public Subscriber createSubscriber(Subscriber subscriber) {
        doesSubscriberAlreadyExist(subscriber);
        System.out.println("============================================");
        System.out.println("New subscriber : " + subscriber);
        System.out.println("============================================\n");
        register(subscriber);
        return subscriber;
    }

    @Override
    public Subscriber updateSubscriberStatus(Subscriber subscriber) {
        register(subscriber);
        return subscriber;
    }

    @Override
    public void register(Subscriber subscriber) {
        data.put(subscriber.getSubscriberId(), subscriber);
    }

    @Override
    public List<Subscriber> getAllActive() {
        return new ArrayList<>(data.values()); // Apply filter
    }

    @Override
    public String nextId() {
        return String.valueOf(counter.incrementAndGet());
    }
}
