package edu.hw8.task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import lombok.SneakyThrows;

public class FixedThreadPull implements ThreadPool {
    private static final Integer QUEUE_CAPACITY = 1000;
    private volatile Boolean isShutdown = false;
    private final BlockingQueue<Runnable> workQueue;
    private final int allThreads;
    private final Thread[] workingThreads;

    private FixedThreadPull(int numberOfThreads) {
        this.allThreads = numberOfThreads;
        this.workQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        this.workingThreads = new Thread[numberOfThreads];
    }

    @Override
    public void start() {
        for (int i = 0; i < allThreads; i++) {
            Thread thread = new Thread(new InfiniteThread());
            thread.start();
            workingThreads[i] = thread;
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isShutdown) {
            throw new RuntimeException("Thread pull interrupted");
        }
        workQueue.add(runnable);
    }

    @Override
    public void close() {
        isShutdown = true;
        workQueue.clear();
        for (var thread : workingThreads) {
            thread.interrupt();
        }
    }

    public static FixedThreadPull create(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            throw new RuntimeException("Количество потоков должно быть больше 0");
        }
        return new FixedThreadPull(numberOfThreads);
    }

    private final class InfiniteThread implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            while (!isShutdown) {
                if (!workQueue.isEmpty()) {
                    Runnable task = workQueue.take();
                    task.run();
                }
            }
        }
    }
}
