package edu.project3.metricsTest;

import edu.project3.TestData;
import edu.project3.metrics.dataProviders.Metric;
import edu.project3.metrics.dataProviders.RequestCounterMetric;
import edu.project3.types.MetricsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestCounterMetricTest {
    @Test
    @DisplayName("One file resources metric build test")
    public void build_shouldReturnMetricForResourcesInfo() {
        Metric requestCounterMetric = new RequestCounterMetric();
        requestCounterMetric.createMetric(TestData.metricParams);
        assertThat(requestCounterMetric.getMetricsData())
            .usingRecursiveComparison()
            .ignoringCollectionOrderInFields()
            .isEqualTo(
                MetricsData.builder()
                    .title(
                        "Запрашиваемые ресурсы")
                    .data(new String[][] {
                        {"Ресурс", "`contingency%20Future-proofed.css`" , "`orchestration.png`", "`Focused-encoding.svg`","`info-mediaries.php`",   "`internet%20solution_Re-contextualized.css`", "`Cross-platform-neutral.css`"},
                        {"Количество", "2", "1", "1", "1", "1", "1"}
                    })
            );
    }
}
