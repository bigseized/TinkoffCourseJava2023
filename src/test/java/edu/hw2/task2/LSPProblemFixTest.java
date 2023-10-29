package edu.hw2.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LSPProblemFixTest {
    static Arguments[] rectangles_test() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles_test")
    @DisplayName("Оба значения заданы корректно")
    void rectangleArea_test3(Rectangle rect) {
        Rectangle newRect = rect.setHeight(20);
        Rectangle ansRect = newRect.setWidth(10);

        assertThat(ansRect.area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Проверка установки длины стороны квадрата и вычисление площади")
    void rectangleArea_test1() {
        Square square = new Square().setSide(10);

        assertThat(square.area()).isEqualTo(100);
    }

    @Test
    @DisplayName("Проверка на создание Rectangle из квадрата и вычисление площади")
    void rectangleArea_test2() {
        Square square = new Square(5);

        assertAll(
            () -> assertThat(square.setHeight(1)).isInstanceOf(Rectangle.class),
            () -> assertThat(square.area()).isEqualTo(25)
        );
    }
}
