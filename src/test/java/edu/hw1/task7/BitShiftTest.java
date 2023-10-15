package edu.hw1.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class BitShiftTest {
    @ParameterizedTest
    @CsvSource(value = {
        "8,1,4",
        "2,1,1",
        "0,2,0",
        "3,10,3",
        "-4,10,-1",
        "4,-12,-1"
    })
    @DisplayName("Тест сдвига вправо")
    public void rotateLeft_shouldReturnValue_whenCorrectInput(int input, int shift, int ans) {
        assertThat(BitShift.rotateRight(input, shift)).isEqualTo(ans);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "17,2,6",
        "16,1,1",
        "2,1,1",
        "0,2,0",
        "3,10,3",
        "-4,10,-1",
        "4,-12,-1"
    })
    @DisplayName("Тест сдвига влево")
    public void rotateRight_shouldReturnValue_whenCorrectInput(int input, int shift, int ans) {
        assertThat(BitShift.rotateLeft(input, shift)).isEqualTo(ans);
    }
}
