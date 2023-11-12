package edu.hw5.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static edu.hw5.task7.RegexUtils.*;

public class RegexUtilsTest {
    private static Stream<Arguments> firstRegexCorrectInputTest() {
        return Stream.of(
            Arguments.of("010"),
            Arguments.of("000"),
            Arguments.of("110"),
            Arguments.of("11010101010")
        );
    }

    private static Stream<Arguments> firstRegexIncorrectInputTest() {
        return Stream.of(
            Arguments.of("011"),
            Arguments.of("001"),
            Arguments.of("111"),
            Arguments.of("11110101010"),
            Arguments.of("00"),
            Arguments.of(""),
            Arguments.of("a10"),
            Arguments.of("210")
        );
    }

    private static Stream<Arguments> secondRegexCorrectInputTest() {
        return Stream.of(
            Arguments.of("00"),
            Arguments.of("11"),
            Arguments.of("1010101010011"),
            Arguments.of("0010101010010")
        );
    }

    private static Stream<Arguments> secondRegexIncorrectInputTest() {
        return Stream.of(
            Arguments.of("1"),
            Arguments.of("0"),
            Arguments.of("10"),
            Arguments.of("01"),
            Arguments.of("11110101010"),
            Arguments.of("0110101010101"),
            Arguments.of("022220"),
            Arguments.of("0aaaaa0"),
            Arguments.of("")
        );
    }

    private static Stream<Arguments> thirdRegexCorrectInputTest() {
        return Stream.of(
            Arguments.of("0"),
            Arguments.of("1"),
            Arguments.of("01"),
            Arguments.of("10"),
            Arguments.of("000")
        );
    }

    private static Stream<Arguments> thirdRegexIncorrectInputTest() {
        return Stream.of(
            Arguments.of("1000"),
            Arguments.of("100111000"),
            Arguments.of("02"),
            Arguments.of("0a"),
            Arguments.of("")
        );
    }

    @ParameterizedTest
    @MethodSource("firstRegexCorrectInputTest")
    @DisplayName("Первый паттерн подходящая строка")
    public void test1(String zerosAndOnes) {
        assertTrue(isMatchesFirst(zerosAndOnes));
    }

    @ParameterizedTest
    @MethodSource("firstRegexIncorrectInputTest")
    @DisplayName("Первый паттерн неподходящая строка")
    public void test2(String zerosAndOnes) {
        assertFalse(isMatchesFirst(zerosAndOnes));
    }

    @ParameterizedTest
    @MethodSource("secondRegexCorrectInputTest")
    @DisplayName("Второй паттерн подходящая строка")
    public void test3(String zerosAndOnes) {
        assertTrue(isMatchesSecond(zerosAndOnes));
    }

    @ParameterizedTest
    @MethodSource("secondRegexIncorrectInputTest")
    @DisplayName("Второй паттерн неподходящая строка")
    public void test4(String zerosAndOnes) {
        assertFalse(isMatchesSecond(zerosAndOnes));
    }


    @ParameterizedTest
    @MethodSource("thirdRegexCorrectInputTest")
    @DisplayName("Третий паттерн подходящая строка")
    public void test5(String zerosAndOnes) {
        assertTrue(isMatchesThird(zerosAndOnes));
    }

    @ParameterizedTest
    @MethodSource("thirdRegexIncorrectInputTest")
    @DisplayName("Второй паттерн неподходящая строка")
    public void test6(String zerosAndOnes) {
        assertFalse(isMatchesThird(zerosAndOnes));
    }
}
