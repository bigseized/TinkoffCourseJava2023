package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {

    private final static double PERCENT_25 = 0.25;

    @Override
    public Connection getConnection() {
        if (Math.random() <= PERCENT_25) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
