package edu.project1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleHangmanTest {
    private List<LogRecord> logs;
    private ByteArrayInputStream in;
    private InputStream originalInputStream;

    @BeforeEach
    public void setUp() {
        logs = new ArrayList<>();
        originalInputStream = System.in;
    }


    @AfterEach
    public void restoreSystemInput() {
        System.setIn(originalInputStream);
    }

    @Test
    @DisplayName("Проверка вывода программы при опечатке")
    void check_GameRunning_IfIncorrectInput() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(ConsoleHangman.class.getName());
        in = new ByteArrayInputStream("eqwewq".getBytes());
        System.setIn(in);
        ConsoleHangman consoleHangman = new ConsoleHangman("rando");
        logger.addHandler(getNewHandler());

        try {
            consoleHangman.run();
        } catch (NoSuchElementException ignored) {
        }

        assertEquals(2, logs.size());
        LogRecord logRecord = logs.get(1);
        assertEquals("Введите один символ для продолжения или \"?\" для выхода ", logRecord.getMessage());
    }

    @Test
    @DisplayName("Проверка вывода программы при вводе символа остановки")
    void check_GameStopped_IfStopSymbolInput() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(ConsoleHangman.class.getName());
        in = new ByteArrayInputStream("?".getBytes());
        System.setIn(in);
        ConsoleHangman consoleHangman = new ConsoleHangman("rando");
        logger.addHandler(getNewHandler());

        try {
            consoleHangman.run();
        } catch (NoSuchElementException ignored) {
        }

        assertEquals(3, logs.size());
        LogRecord logRecord = logs.get(1);
        assertEquals("Игра остановлена вводом специального символа\n ", logRecord.getMessage());
        logRecord = logs.get(2);
        assertEquals("You lost!", logRecord.getMessage());
    }

    @Test
    @DisplayName("Проверка вывода программы при вводе корректного символа")
    void check_GameWork_IfCorrectInput() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(ConsoleHangman.class.getName());
        in = new ByteArrayInputStream("a".getBytes());
        System.setIn(in);
        ConsoleHangman consoleHangman = new ConsoleHangman("rando");
        logger.addHandler(getNewHandler());

        try {
            consoleHangman.run();
        } catch (NoSuchElementException ignored) {
        }

        assertEquals(4, logs.size());
        LogRecord logRecord = logs.get(1);
        assertEquals("Hit!\n", logRecord.getMessage());
    }

    private Handler getNewHandler(){
        return new Handler() {
            @Override
            public void publish(LogRecord record) {
                logs.add(record);
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() throws SecurityException {
            }
        };
    }
}
