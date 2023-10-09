package edu.hw1.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CountDigitsTest {
    @ParameterizedTest
    @CsvSource(value = {
        "4666, 4",
        "544, 3",
        "0, 1",
        "22, 2",
        "100, 3",
        "9, 1",
        "10, 2"
    })
    @DisplayName("Ввод корректной строки")
    public void countDigits_shouldReturnValue_whenCorrectInput(int input, int ans) {
        assertThat(CountDigits.countDigits(input)).isEqualTo(ans);
    }
}
