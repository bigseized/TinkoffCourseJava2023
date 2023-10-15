package edu.hw2.task3;

import java.util.logging.Logger;

public class StableConnection implements Connection{

    private static Logger log = Logger.getLogger(FaultyConnection.class.getName());

    @Override
    public void execute(String command) {
    }

    @Override
    public void close() {
        log.info("Stable connection is closed");
    }
}
