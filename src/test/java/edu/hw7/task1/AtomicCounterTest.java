package edu.hw7.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtomicCounterTest {

    @Test
    @DisplayName("Increment test")
    public void increment_shouldReturnCorrectValue() {
        assertEquals(AtomicCounter.FIRST_NUMBER + AtomicCounter.SECOND_NUMBER, AtomicCounter.count());
    }
}
