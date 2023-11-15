package edu.hw5.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static edu.hw5.task8.RegexBonusUtils.*;

public final class RegexBonusUtilsTest {
    private static Stream<Arguments> firstRegexTest() {
        return Stream.of(
            Arguments.of("010", true),
            Arguments.of("0", true),
            Arguments.of("11", false),
            Arguments.of("222", false)
        );
    }

    private static Stream<Arguments> secondRegexTest() {
        return Stream.of(
            Arguments.of("10", true),
            Arguments.of("0", true),
            Arguments.of("1011", true),
            Arguments.of("01101", true),
            Arguments.of("100", false),
            Arguments.of("01", false)
        );
    }

    private static Stream<Arguments> thirdRegexTest() {
        return Stream.of(
            Arguments.of("1111", true),
            Arguments.of("000", true),
            Arguments.of("1000111", true),
            Arguments.of("100", false),
            Arguments.of("01", false)
        );
    }

    private static Stream<Arguments> fourthRegexTest() {
        return Stream.of(
            Arguments.of("1111", true),
            Arguments.of("000", true),
            Arguments.of("1000111", true),
            Arguments.of("", true),
            Arguments.of("11", false),
            Arguments.of("111", false)
        );
    }

    private static Stream<Arguments> fifthRegexTest() {
        return Stream.of(
            Arguments.of("10101", true),
            Arguments.of("1010", true),
            Arguments.of("11", true),
            Arguments.of("0001", false)
        );
    }

    private static Stream<Arguments> sixthRegexTest() {
        return Stream.of(
            Arguments.of("001", true),
            Arguments.of("10000", true),
            Arguments.of("000", true),
            Arguments.of("00010000", true),
            Arguments.of("01", false),
            Arguments.of("000111", false),
            Arguments.of("", false)
        );
    }

    private static Stream<Arguments> seventhRegexTest() {
        return Stream.of(
            Arguments.of("10101", true),
            Arguments.of("1", true),
            Arguments.of("0", true),
            Arguments.of("1000", true),
            Arguments.of("10001001", true),
            Arguments.of("11000100000000000000001000000001", false),
            Arguments.of("0000001", true),
            Arguments.of("01010", true),
            Arguments.of("1010", true),
            Arguments.of("0101", true),
            Arguments.of("0110101", false),
            Arguments.of("11111", false)
        );
    }

    @ParameterizedTest
    @MethodSource("firstRegexTest")
    @DisplayName("Проверка 1 паттерна")
    public void test1(String input, boolean expected) {
        assertThat(isMatchesFirst(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("secondRegexTest")
    @DisplayName("Проверка 2 паттерна")
    public void test2(
        String input,
        boolean expected
    ) {
        assertThat(isMatchesSecond(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("thirdRegexTest")
    @DisplayName("Проверка 3 паттерна")
    public void test3(
        String input,
        boolean expected
    ) {
        assertThat(isMatchesThird(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("fourthRegexTest")
    @DisplayName("Проверка 4 паттерна")
    public void test4(
        String input,
        boolean expected
    ) {
        assertThat(isMatchesFourth(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("fifthRegexTest")
    @DisplayName("Проверка 5 паттерна")
    public void test5(
        String input,
        boolean expected
    ) {
        assertThat(isMatchesFifth(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("sixthRegexTest")
    @DisplayName("Проверка 6 паттерна")
    public void test6(
        String input,
        boolean expected
    ) {
        assertThat(isMatchesSixth(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("seventhRegexTest")
    @DisplayName("Проверка 7 паттерна")
    public void test7(
        String input,
        boolean expected
    ) {
        assertThat(isMatchesSeventh(input)).isEqualTo(expected);
    }
}
