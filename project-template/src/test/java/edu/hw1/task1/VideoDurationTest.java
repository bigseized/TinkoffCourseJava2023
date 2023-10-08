package edu.hw1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class VideoDurationTest {
    @ParameterizedTest
    @CsvSource(value = {
        "01:00, 60",
        "13:56, 836",
        "10:60, -1",
        "fsdf, -1",
        "13:62, -1",
        "13:62:32, -1",
    })
    @DisplayName("Ввод корректной строки")
    public void minutesToSeconds_shouldReturnValue_whenCorrectInput(String input, int ans) {
        assertThat(VideoDuration.minutesToSeconds(input)).isEqualTo(ans);
    }
}
