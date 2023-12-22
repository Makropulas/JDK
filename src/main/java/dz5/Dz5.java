package dz5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dz5 {
    private static int ingestion;
    private static final Lock locker = new ReentrantLock();

    public static void main(String[] args) {
        ingestion = 3;
        philosopherHasLunch("Аристотель");
        philosopherHasLunch("Сократ");
        philosopherHasLunch("Омар Хайям");
        philosopherHasLunch("Фома Аквинский");
        philosopherHasLunch("Рене Декарт");
    }

    public static void philosopherHasLunch(String name) {
        new Philosopher(name, ingestion, locker);
    }
}
