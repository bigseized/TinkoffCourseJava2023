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
    })
    @DisplayName("Ввод корректной строки")
    public void minutesToSeconds_shouldReturnValue_whenCorrectInput(String input, int ans) {
        assertThat(VideoDuration.convertTimeToSeconds(input)).isEqualTo(ans);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "fsdf, -1",
        "13:62:32, -1"
    })
    @DisplayName("Ввод строки неправильного формата")
    public void minutesToSeconds_shouldReturnValue_whenIncorrectStringFormat(String input, int ans) {
        assertThat(VideoDuration.convertTimeToSeconds(input)).isEqualTo(ans);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "10:60, -1",
        "13:62, -1",
        "-4:12, -1"
    })
    @DisplayName("Ввод строки c неправильным временем")
    public void minutesToSeconds_shouldReturnValue_whenIncorrectTime(String input, int ans) {
        assertThat(VideoDuration.convertTimeToSeconds(input)).isEqualTo(ans);
    }
}
