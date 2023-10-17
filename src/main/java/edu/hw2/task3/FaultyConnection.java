package edu.hw2.task3;

import java.util.logging.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = Logger.getLogger(FaultyConnection.class.getName());
    private final static double PERCENT_50 = 0.5;

    @Override
    public void execute(String command) throws ConnectionException {
        if (Math.random() <= PERCENT_50) {
            throw new ConnectionException("Не удалось установить соединение");
        }
        //работа после установки соединения
    }


    @Override
    public void close() {
        LOGGER.info("Faulty connection is closed");
    }
}
