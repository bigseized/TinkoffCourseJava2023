package edu.hw6.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PortCheckTest {
    @Test
    @DisplayName("printUsedPorts test")
    public void printUsedPorts_shouldPrintListOfUsedPorts() {
        assertDoesNotThrow(PortCheck::scanPorts);
    }
}
