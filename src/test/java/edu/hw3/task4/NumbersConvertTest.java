package edu.hw3.task4;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class NumbersConvertTest {

    private static Stream<Arguments> getArgumentWhenCorrectInput() {
        return Stream.of(
            Arguments.of(3, "III"),
            Arguments.of(789, "DCCLXXXIX"),
            Arguments.of(1, "I"),
            Arguments.of(3999, "MMMCMXCIX"),
            Arguments.of(173, "CLXXIII")
        );
    }

    @ParameterizedTest
    @MethodSource("getArgumentWhenCorrectInput")
    @DisplayName("Проверка на перевод корректных чисел")
    public void number_shouldReturnConvertedToRomanNumber_whenCorrectInput(int value, String ans) {
        assertThat(NumbersConvert.convertToRoman(value)).isEqualTo(ans);
    }

    @ParameterizedTest
    @CsvSource({
        "0",
        "4000",
        "-123"
    })
    @DisplayName("Проверка на неправильный ввод")
    public void number_shouldThrowException_whenInputIsIncorrect(int value) {
        assertThatThrownBy(() -> NumbersConvert.convertToRoman(value)).isInstanceOf(IllegalArgumentException.class);
    }
}
