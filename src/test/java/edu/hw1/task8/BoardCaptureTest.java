package edu.hw1.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardCaptureTest {

    @Test
    @DisplayName("Test 1 -> true")
    public void boardCapture_shouldReturnValue_whenCorrectInput_1Test() {
        int[][] board  = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}};
        boolean ans = true;
        assertThat(BoardCapture.knightBoardCapture(board)).isEqualTo(ans);
    }

    @Test
    @DisplayName("Test 2 -> false")
    public void boardCapture_shouldReturnValue_whenCorrectInput_2Test() {
        int[][] board  = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };
        boolean ans = false;
        assertThat(BoardCapture.knightBoardCapture(board)).isEqualTo(ans);
    }

    @Test
    @DisplayName("Test 3 -> false")
    public void boardCapture_shouldReturnValue_whenCorrectInput_3Test() {
        int[][] board  = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };
        boolean ans = false;
        assertThat(BoardCapture.knightBoardCapture(board)).isEqualTo(ans);
    }

}
