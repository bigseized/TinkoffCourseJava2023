package edu.project3;

import edu.project3.metrics.renderers.MarkdownRenderer;
import edu.project3.types.MetricsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownRendererTest {

    @Test
    @DisplayName("Markdown format test")
    public void print_shouldReturnStringInMarkdownFormat() {
        MetricsData metricsData = MetricsData
            .builder()
            .title("Test")
            .data(new String[][] {new String[] {"Test column", "first", "second"}, {"Number", "1", "2"}})
            .build();
        String renderedTable = new MarkdownRenderer().render(metricsData);
        String formattedString = """
            #### Test

            | Test column | Number |
            |:-----------:|:------:|
            |       first |      1 |
            |      second |      2 |
            """;
        assertThat(renderedTable).isEqualTo(formattedString);
    }
}
