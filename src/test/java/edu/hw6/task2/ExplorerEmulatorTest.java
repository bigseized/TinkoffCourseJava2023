package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static edu.hw6.task2.ExplorerEmulator.cloneFile;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExplorerEmulatorTest {
    private Path filePath;
    private Path copyPath;
    private Path secondCopyPath;

    @TempDir
    Path tempDir;

    @BeforeEach
    @SneakyThrows
    public void init() {
        filePath = Path.of(String.valueOf(tempDir), "test" + ".txt");
        copyPath = Path.of(String.valueOf(tempDir), "test - копия" + ".txt");
        secondCopyPath = Path.of(String.valueOf(tempDir), "test - копия (2)" + ".txt");
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

    @Test
    @DisplayName("Second copy test")
    public void cloneFiles_shouldCreateSecondCopy() {
        cloneFile(filePath);
        cloneFile(filePath);
        cloneFile(filePath);
        assertTrue(Files.exists(secondCopyPath));
    }
}
