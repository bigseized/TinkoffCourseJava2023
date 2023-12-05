package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AtomicCounter {
    private final AtomicInteger counter = new AtomicInteger(0);
    public final static int FIRST_NUMBER = 100;
    public final static int SECOND_NUMBER = 15;

    @SneakyThrows
    public static int count() {
        Thread first = new Thread(() -> {
            for (int i = 0; i < FIRST_NUMBER; i++) {
                counter.getAndIncrement();
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < SECOND_NUMBER; i++) {
                counter.getAndIncrement();
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        return counter.get();
    }
}
