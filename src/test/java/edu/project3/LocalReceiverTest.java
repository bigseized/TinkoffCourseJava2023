package edu.project3;

import edu.project3.dataPreparation.dataParsers.LocalPathParser;
import edu.project3.dataPreparation.dataReceivers.LocalReceiver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalReceiverTest {
    @Test
    @DisplayName("Path receive logs test")
    public void receive_shouldReturnListOfStringsFromPathsToFiles() throws FileNotFoundException {
        List<String> logs = LocalReceiver.loadLogs(LocalPathParser.parseLocalPath("src/main/resources/**"));

    }

    @Test
    @DisplayName("Path receive logs with wrong path test")
    public void receive_shouldThrowException_whenPathIsWrong() throws FileNotFoundException {
        String wrongPath = "src/main/**/logs/log55.txt";
        List<String> logs = LocalReceiver.loadLogs(LocalPathParser.parseLocalPath(wrongPath));
        assertThat(logs).isEmpty();

    }
}
