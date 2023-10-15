package edu.hw1.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeTest {
    @ParameterizedTest
    @CsvSource(value = {

        "11, true",
        "121, true",
    })
    @DisplayName("Ввод строк являющихся палиндромом")
    public void Palindrome_shouldReturnValue_whenNumberIsPalindrome(int input, boolean ans) {
        assertThat(Palindrome.isPalindromeDescendant(input)).isEqualTo(ans);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "11211230, true",
        "13001120, true",
        "102001, true"
    })
    @DisplayName("Ввод строк содержащих палиндром")
    public void Palindrome_shouldReturnValue_whenNumberContainsPalindrome(int input, boolean ans) {
        assertThat(Palindrome.isPalindromeDescendant(input)).isEqualTo(ans);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "12, false",
        "0, false",
        "4234234, false",
    })
    @DisplayName("Ввод строк не содержащих палиндрмом")
    public void Palindrome_shouldReturnValue_whenNumberNotPalindrome(int input, boolean ans) {
        assertThat(Palindrome.isPalindromeDescendant(input)).isEqualTo(ans);
    }
}
