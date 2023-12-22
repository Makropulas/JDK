package dz5;

import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread {
    private final String name;
    private int ingestion;
    private final Lock locker;

    public Philosopher(String name, int ingestion, Lock locker) {
        this.name = name;
        this.ingestion = ingestion;
        this.locker = locker;
        this.setPriority(MAX_PRIORITY);
        this.start();
    }

    @Override
    public void run() {
        int mediumPriorityReduction = MAX_PRIORITY / ingestion;
        while (ingestion != 0) {
            if (locker.tryLock()) {
                try {
                    System.out.println(name + " обедает");
                    sleep(500);
                    ingestion--;
                    this.setPriority(Math.max(getPriority() - mediumPriorityReduction, MIN_PRIORITY));
                    System.out.println(name + " размышляет");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    locker.unlock();
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
