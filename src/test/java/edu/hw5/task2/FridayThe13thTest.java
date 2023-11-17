package edu.hw5.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static edu.hw5.task2.FridayThe13th.*;

public final class FridayThe13thTest {
    private static Stream<Arguments> yearsForFridayFinderTest() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of("1925-02-13", "1925-03-13", "1925-11-13")
            ),
            Arguments.of(
                2024,
                List.of("2024-09-13", "2024-12-13")
            )
        );
    }

    private static Stream<Arguments> datesForNextFridayFinderTest() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(1925, 1, 13),
                LocalDate.of(1925, 2, 13)
            ),
            Arguments.of(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("yearsForFridayFinderTest")
    @DisplayName("Все плохие пятницы в году")
    public void test1(int year, List<String> expected) {
        assertThat(findBadFridays(year)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("datesForNextFridayFinderTest")
    @DisplayName("Следующая плохая пятница")
    public void test2(LocalDate date, LocalDate nextFriday) {
        assertThat(findNextBadFriday(date)).isEqualTo(nextFriday);
    }

}
