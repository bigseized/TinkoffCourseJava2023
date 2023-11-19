package edu.project3;

import edu.project3.metrics.renderers.AdocRenderer;
import edu.project3.types.MetricsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdocRendererTest {

    @Test
    @DisplayName("Adoc format test")
    public void print_shouldReturnStringInAdocFormat() {
        MetricsData metricsData = MetricsData
            .builder()
            .title("Test")
            .data(new String[][]{new String[]{"Test column", "first", "second"},{"Number", "1", "2"}})
            .build();
        String renderedTable = new AdocRenderer().render(metricsData);
        String formattedString = """
            ==== Test

            |=====================
            | Test column | Number\s

            |       first |      1\s
            |      second |      2\s
            |=====================
            """;
        assertThat(renderedTable).isEqualTo(formattedString);
    }
}
