package edu.hw8.task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import lombok.SneakyThrows;

public class FixedThreadPull implements ThreadPool {
    private static volatile Boolean isShutdown = false;
    private static final Integer QUEUE_CAPACITY = 1000;
    private final BlockingQueue<Runnable> workQueue;
    private final Integer allThreads;

    private FixedThreadPull(int numberOfThreads) {
        this.allThreads = numberOfThreads;
        this.workQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    }

    @Override
    public void start() {
        if (allThreads == null) {
            throw new RuntimeException("Вызов метода до создания потока");
        }
        for (int i = 0; i < allThreads; i++) {
            Thread thread = new Thread(new InfiniteThread());
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        workQueue.add(runnable);
    }

    @Override
    public void close() {
        isShutdown = true;
    }

    public static FixedThreadPull create(int numberOfThreads) {
        return new FixedThreadPull(numberOfThreads);
    }

    private final class InfiniteThread implements Runnable {
        Runnable task;

        @Override
        @SneakyThrows
        public void run() {
            while (!isShutdown) {
                if (task == null) {
                    if (!workQueue.isEmpty()) {
                        task = workQueue.take();
                        task.run();
                        task = null;
                    }
                }
            }
        }
    }
}
