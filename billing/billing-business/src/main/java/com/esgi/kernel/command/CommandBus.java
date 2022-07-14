package com.esgi.kernel.command;

public interface CommandBus {
    <R, C extends Command> R send(C command);
}
