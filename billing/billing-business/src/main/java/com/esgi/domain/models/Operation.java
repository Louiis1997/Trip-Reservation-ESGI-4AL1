package com.esgi.domain.models;

import java.time.LocalDate;

public class Operation {
    private final String id;
    private String customerRef;
    private LocalDate operationAt;

    public Operation(String id) {
        this.id = id;
    }
}
