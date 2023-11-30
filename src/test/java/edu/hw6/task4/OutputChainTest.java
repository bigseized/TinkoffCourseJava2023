package edu.hw6.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw6.task4.OutputChain.chain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OutputChainTest {
    private final Path filePath = Paths.get("src/main/java/edu/hw6/task4/newFile");
    private final String line = "Programming is learned by writing programs. ― Brian Kernighan";

    @Test
    @DisplayName("Creation of file test")
    public void outputChain_shouldCreateFile() throws IOException{
        chain();
        assertTrue(Files.exists(filePath));
    }

    @Test
    @DisplayName("Created file has correct line inside")
    public void outputChain_shouldWriteLineInCreatedFile() throws IOException {
        chain();
        assertEquals(line, Files.readAllLines(filePath).get(0));
    }
}
