package edu.project2;

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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeSolverTest {
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
    @DisplayName("Проверка вывода программы при вводе неправильного размера лабиринта (меньше 2 чисел)")
    void check_if_incorrect_input_test1() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeSolver.class.getName());
        in = new ByteArrayInputStream("?".getBytes());
        System.setIn(in);
        MazeSolver mazeSolver = new MazeSolver();
        logger.addHandler(getNewHandler());

        try {
            mazeSolver.run();
        } catch (NoSuchElementException ignored) {
        }

        assertAll(
            () -> assertEquals(2, logs.size()),
            () -> assertEquals("Введите размер лабиринта:", logs.get(0).getMessage()),
            () -> assertEquals("Необходимо ввести 2 числа!", logs.get(1).getMessage())

        );
    }

    @Test
    @DisplayName("Проверка вывода программы при вводе неправильного размера лабиринта (меньше минимального размера)")
    void check_if_incorrect_input_test2() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeSolver.class.getName());
        in = new ByteArrayInputStream("3 3".getBytes());
        System.setIn(in);
        MazeSolver mazeSolver = new MazeSolver();
        logger.addHandler(getNewHandler());

        try {
            mazeSolver.run();
        } catch (NoSuchElementException ignored) {
        }

        assertAll(
            () -> assertEquals(2, logs.size()),
            () -> assertEquals("Введите размер лабиринта:", logs.get(0).getMessage()),
            () -> assertEquals("Размер лабиринта не может быть меньше 3", logs.get(1).getMessage())
        );
    }

    @Test
    @DisplayName("Проверка вывода программы при вводе неправильного размера лабиринта (чётный размер)")
    void check_if_incorrect_input_test3() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeSolver.class.getName());
        in = new ByteArrayInputStream("12 12".getBytes());
        System.setIn(in);
        MazeSolver mazeSolver = new MazeSolver();
        logger.addHandler(getNewHandler());

        try {
            mazeSolver.run();
        } catch (NoSuchElementException ignored) {
        }

        assertAll(
            () -> assertEquals(2, logs.size()),
            () -> assertEquals("Введите размер лабиринта:", logs.get(0).getMessage()),
            () -> assertEquals("Размеры лабиринта не могут быть чётными!", logs.get(1).getMessage())
        );
    }

    @Test
    @DisplayName("Проверка вывода программы при вводе правильного размера лабиринта")
    void check_if_correct_input_test1() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeSolver.class.getName());
        in = new ByteArrayInputStream("13 13".getBytes());
        System.setIn(in);
        MazeSolver mazeSolver = new MazeSolver();
        logger.addHandler(getNewHandler());

        try {
            mazeSolver.run();
        } catch (NoSuchElementException ignored) {
        }

        assertAll(
            () -> assertEquals(2, logs.size()),
            () -> assertEquals("Введите размер лабиринта:", logs.get(0).getMessage()),
            () -> assertEquals(
                    """
                            Выберите алгоритм генерации:
                            1) Алгоритм Прима
                            2) Алгоритм DFS""",
                logs.get(1).getMessage()
            )
        );
    }

    @Test
    @DisplayName("Проверка вывода программы при вводе неправильного варианта генерации лабиринта")
    void check_if_incorrect_input_test4() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeSolver.class.getName());
        in = new ByteArrayInputStream("13 13 \n3".getBytes());
        System.setIn(in);
        MazeSolver mazeSolver = new MazeSolver();
        logger.addHandler(getNewHandler());

        try {
            mazeSolver.run();
        } catch (NoSuchElementException ignored) {
        }

        assertAll(
            () -> assertEquals(3, logs.size()),
            () -> assertEquals("Введите 1 или 2 для выбора варианта!", logs.get(2).getMessage())
        );
    }

    @Test
    @DisplayName("Проверка вывода программы при вводе правильного выбора вариантов")
    void check_if_correct_input_test2() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeSolver.class.getName());
        in = new ByteArrayInputStream("13 13 \n2\n2".getBytes());
        System.setIn(in);
        MazeSolver mazeSolver = new MazeSolver();
        logger.addHandler(getNewHandler());

        try {
            mazeSolver.run();
        } catch (NoSuchElementException ignored) {
        }

        assertAll(
            () -> assertEquals(5, logs.size()),
            () -> assertEquals("Введите координаты начала решения:", logs.get(4).getMessage())
        );
    }

    @Test
    @DisplayName("Проверка вывода программы при вводе неправильных координат для решения")
    void check_if_incorrect_input_test5() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeSolver.class.getName());
        in = new ByteArrayInputStream("13 13 \n2\n2\n0 0\n14 14".getBytes());
        System.setIn(in);
        MazeSolver mazeSolver = new MazeSolver();
        logger.addHandler(getNewHandler());

        try {
            mazeSolver.run();
        } catch (NoSuchElementException ignored) {
        }

        assertAll(
            () -> assertEquals(7, logs.size()),
            () -> assertEquals("Точка является стеной!", logs.get(5).getMessage()),
            () -> assertEquals("Координаты выходят за границы лабиринта!", logs.get(6).getMessage())
        );
    }

    @Test
    @DisplayName("Проверка вывода программы при вводе правильных координат для решения")
    void check_if_correct_input_test3() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeSolver.class.getName());
        in = new ByteArrayInputStream("13 13 \n2\n2\n1 1\n11 11".getBytes());
        System.setIn(in);
        MazeSolver mazeSolver = new MazeSolver();
        logger.addHandler(getNewHandler());

        try {
            mazeSolver.run();
        } catch (NoSuchElementException ignored) {
        }

        assertAll(
            () -> assertEquals(6, logs.size()),
            () -> assertEquals("Введите координаты конца решения:", logs.get(5).getMessage())
        );
    }

    private Handler getNewHandler() {
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
