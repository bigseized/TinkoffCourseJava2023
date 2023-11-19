package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BaseLogAnalyzerTest {
    private final String[] args = {"--path", "src/**/logs.txt", "--format", "adoc"};

    @Test
    @DisplayName("Application start test")
    public void run_shouldNotThrowAnyException() {
        BaseLogAnalyzer baseLogAnalyzer = new BaseLogAnalyzer(args);
        assertDoesNotThrow(baseLogAnalyzer::run);
    }
}
