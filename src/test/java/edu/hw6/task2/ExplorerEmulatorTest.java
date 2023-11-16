package edu.hw6.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.task2.ExplorerEmulator.cloneFile;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExplorerEmulatorTest {
    private final Path filePath = Path
        .of("src/main/java/edu/hw6/task2/test.txt");
    private final Path copyPath = Path.of("src/main/java/edu/hw6/task2/test - копия.txt");
    private final Path secondCopyPath = Path.of("src/main/java/edu/hw6/task2/test - копия (2).txt");

    @BeforeEach
    void deleteCopies() throws IOException {
        Files.deleteIfExists(filePath);
        Files.deleteIfExists(copyPath);
        Files.deleteIfExists(secondCopyPath);
    }

    @Test
    @DisplayName("First copy exist test")
    public void cloneFiles_shouldCreateFile() {
        cloneFile(filePath);
        assertTrue(Files.exists(filePath));
    }

    @Test
    @DisplayName("Second copy exist test")
    public void cloneFiles_shouldCreateSecondCopyOfFile() {
        cloneFile(filePath);
        cloneFile(filePath);
        assertTrue(Files.exists(copyPath));
    }

    @Test
    @DisplayName("First copy delete then clone again")
    public void cloneFiles_shouldCreateFirstCopyOfFile_whenSecondCopyExists() throws IOException {
        cloneFile(filePath);
        cloneFile(filePath);
        Files.delete(copyPath);
        cloneFile(filePath);
        assertTrue(Files.exists(copyPath));
    }
}
