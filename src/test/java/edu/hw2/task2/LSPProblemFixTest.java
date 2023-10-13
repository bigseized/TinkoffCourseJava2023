package edu.hw2.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;



public class LSPProblemFixTest {
    static Arguments[] rectangles_test1() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles_test1")
    void rectangleArea_test1(Rectangle rect) {
        rect.setHeight(10);
        rect.setWidth(0);
        assertThat(rect.area()).isEqualTo(0.0);
    }

    static Arguments[] rectangles_test2() {
        return new Arguments[]{
            //Arguments.of(new Rectangle()), Тест выводит 0, как и в базовом классе при отсутствии Square
            Arguments.of(new Square()) //Тест выводит 100 тк задана только одна сторона
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles_test2")
    void rectangleArea_test2(Rectangle rect) {
        rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(100.0);
    }

    static Arguments[] rectangles_test3() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles_test3")
    void rectangleArea_test3(Rectangle rect) {
        rect.setHeight(20);
        rect.setWidth(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }
}
