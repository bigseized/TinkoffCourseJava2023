package edu.hw1.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeTest {
    @ParameterizedTest
    @CsvSource(value = {
        "11211230, true",
        "13001120, true",
        "11, true",
        "12, false",
        "0, false",
        "4234234, false",
        "121, true",
        "102001, true"
    })
    @DisplayName("Ввод корректной строки")
    public void Palindrome_shouldReturnValue_whenCorrectInput(int input, boolean ans) {
        assertThat(Palindrome.isPalindromeDescendant(input)).isEqualTo(ans);
    }
}
