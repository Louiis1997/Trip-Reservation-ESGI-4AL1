package com.esgi.kernel.query;

public interface QueryBus {
    <Q extends Query, R> R send(Q query);
}
