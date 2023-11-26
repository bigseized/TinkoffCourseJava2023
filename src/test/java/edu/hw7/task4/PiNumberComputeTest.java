package edu.hw7.task4;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw7.task4.PiNumberCompute.computePi;
import static edu.hw7.task4.PiNumberCompute.computePiAsync;
import static org.assertj.core.api.Assertions.assertThat;

public class PiNumberComputeTest {
    private static final int ACCURACY = 1000000;
    private static final int NUMBER_OF_CORES = 6;

    @Test
    @DisplayName("approximatePi test")
    public void approximatePi_shouldReturnValueClosedToPi() {
        assertThat(computePi(ACCURACY)).isCloseTo(Math.PI, Offset.offset(0.01));
    }

    @Test
    @DisplayName("approximatePiParallel test")
    public void approximatePiParallel_shouldReturnValueClosedToPi() {
        assertThat(computePiAsync(ACCURACY, NUMBER_OF_CORES)).isCloseTo(Math.PI, Offset.offset(0.01));
    }
}
