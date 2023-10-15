package edu.hw2.task3;

import java.util.logging.Logger;

public class FaultyConnection implements Connection{
    private static Logger log = Logger.getLogger(FaultyConnection.class.getName());
    @Override
    public void execute(String command) throws ConnectionException{
        if (Math.random() * 100 <= 50){
            throw new ConnectionException("Не удалось установить соединение");
        }
    }


    @Override
    public void close() {
        log.info("Faulty connection is closed");
    }
}
