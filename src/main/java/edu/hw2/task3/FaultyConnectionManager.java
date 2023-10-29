package edu.hw2.task3;

import java.util.logging.Logger;

public class FaultyConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(FaultyConnectionManager.class.getName());

    @Override
    public Connection getConnection() {
        LOGGER.info("Faulty connection is opened");
        return new FaultyConnection();
    }
}
