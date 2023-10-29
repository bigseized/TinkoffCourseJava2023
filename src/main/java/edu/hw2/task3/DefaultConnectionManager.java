package edu.hw2.task3;

import java.util.logging.Logger;

public class DefaultConnectionManager implements ConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(DefaultConnectionManager.class.getName());
    private final static double FAULTY_CONNECTION_CHANCE = 0.25;

    @Override
    public Connection getConnection() {
        if (Math.random() <= FAULTY_CONNECTION_CHANCE) {
            LOGGER.info("Faulty connection is opened");
            return new FaultyConnection();
        }
        LOGGER.info("Stable connection is opened");
        return new StableConnection();
    }
}
