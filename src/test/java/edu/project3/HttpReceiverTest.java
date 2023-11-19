package edu.project3;

import edu.project3.dataPreparation.dataReceivers.HttpReceiver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class HttpReceiverTest {
    private static final String HTTP =
        "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

    @Test
    @DisplayName("HTTP receive logs test")
    public void receive_shouldReturnListOfStringsFromHttp() {
        List<String> receiver = HttpReceiver.loadLogs(HTTP);
        assertThat(receiver).isNotEmpty();
    }
}
