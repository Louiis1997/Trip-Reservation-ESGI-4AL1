package com.esgi.kernel.query;

public interface QueryHandler<C extends Query, R> {
    R handle(C command);
}
