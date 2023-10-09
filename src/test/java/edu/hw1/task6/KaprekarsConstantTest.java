package edu.hw1.task6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class KaprekarsConstantTest {
    @ParameterizedTest
    @CsvSource(value = {
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "6174, 0",
        "8352, 1"
    })
    @DisplayName("Ввод корректной строки")
    public void countK_shouldReturnValue_whenCorrectInput(int input, int ans) {
        assertThat(KaprekarsConstant.countK(input)).isEqualTo(ans);
    }
}
