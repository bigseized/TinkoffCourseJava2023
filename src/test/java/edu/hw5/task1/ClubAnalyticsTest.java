package edu.hw5.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.stream.Stream;
import static edu.hw5.task1.ClubAnalytics.calculateAverage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ClubAnalyticsTest {
    private static Stream<Arguments> data() {
        return Stream.of(Arguments.of(new String[]{"2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"},"3ч 40м"));
    }

    @ParameterizedTest
    @MethodSource ("data")
    @DisplayName("Проверка на работу при корректном вводе")
    public void test1(String[] sessions,
        String expected) {
        assertThat(calculateAverage(sessions)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка на некорректный ввод")
    public void test2() {
        String[] sessions = new String[]{
            "2022/03/12, 20:20 - 2022/03/12, 23:50"
        };
        assertThatThrownBy(() -> calculateAverage(sessions)).isInstanceOf(IllegalArgumentException.class);
    }
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Проверка на пустой ввод")
    public void countSession_shouldThrowException_whenListIsNullOrEmpty(String[] sessions) {
        assertThatThrownBy(() -> calculateAverage(sessions)).isInstanceOf(IllegalArgumentException.class);
    }

}
