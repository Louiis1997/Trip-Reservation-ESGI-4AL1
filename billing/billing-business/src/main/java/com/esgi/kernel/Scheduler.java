package com.esgi.kernel;

import java.util.concurrent.TimeUnit;

public interface Scheduler {

    boolean launchScheduler(Runnable task, long delay, long period, TimeUnit unit) throws InterruptedException;

}
