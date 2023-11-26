package edu.hw7.task4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

@UtilityClass
public class PiNumberCompute {
    public final static double RADIUS = 0.5;
    public final static double CENTER = 0.5;
    public final static int MONTE_CARLO_CONST = 4;

    public static double computePi(int accuracy) {
        long totalCount = 0;
        long circleCount = 0;

        for (int i = 0; i < accuracy; i++) {
            Coordinates coordinates = new Coordinates(getRandomDouble(), getRandomDouble());
            if (isInCircle(coordinates)) {
                circleCount++;
            }
            totalCount++;
        }

        return (double) MONTE_CARLO_CONST * circleCount / totalCount;
    }

    @SneakyThrows
    public static double computePiAsync(int accuracy, int numberOfCores) {
        @Cleanup
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfCores);
        Future<Long>[] threadsMethod = new Future[numberOfCores];
        long allDotsInCircle = 0;

        for (int i = 0; i < numberOfCores; i++) {
            threadsMethod[i] = executorService.submit(() -> {
                long circleCount = 0;
                for (int j = 0; j < accuracy / numberOfCores; j++) {
                    Coordinates coordinates = new Coordinates(getRandomDouble(), getRandomDouble());
                    if (isInCircle(coordinates)) {
                        circleCount++;
                    }
                }
                return circleCount;
            });
        }
        for (Future<Long> method : threadsMethod) {
            allDotsInCircle += method.get();
        }

        return (double) MONTE_CARLO_CONST * allDotsInCircle / accuracy;
    }

    private static boolean isInCircle(Coordinates coords) {
        return pow(abs(coords.x - CENTER), 2) + pow(abs(coords.y - CENTER), 2) <= pow(RADIUS, 2);
    }

    private static double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    record Coordinates(double x, double y) {
    }
}


