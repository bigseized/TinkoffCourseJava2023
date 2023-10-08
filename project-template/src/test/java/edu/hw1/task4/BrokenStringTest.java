package edu.hw1.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class BrokenStringTest {
    @ParameterizedTest
    @CsvSource(value = {
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde"
    })
    @DisplayName("Ввод корректной строки")
    public void fixString_shouldReturnValue_whenCorrectInput(String input, String ans) {
        assertThat(BrokenString.fixString(input)).isEqualTo(ans);
    }
}
