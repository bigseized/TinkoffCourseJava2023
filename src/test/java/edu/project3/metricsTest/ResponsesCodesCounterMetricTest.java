package edu.project3.metricsTest;

import edu.project3.TestData;
import edu.project3.metrics.dataProviders.Metric;
import edu.project3.metrics.dataProviders.ResponsesCodesCounterMetric;
import edu.project3.types.MetricsData;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;

public class ResponsesCodesCounterMetricTest {
    @Test
    @DisplayName("One file response metric build test")
    public void build_shouldReturnMetricForResponseInfo() {
        Metric responsesCodesCounterMetric = new ResponsesCodesCounterMetric();
        responsesCodesCounterMetric.createMetric(TestData.metricParams);
        assertThat(responsesCodesCounterMetric.getMetricsData())
            .usingRecursiveComparison()
            .ignoringCollectionOrderInFields()
            .isEqualTo(
                MetricsData.builder()
                    .title(
                        "Коды ответа")
                    .data(new String[][] {
                        {"Код", "200", "400"},
                        {"Имя", "OK", "Bad Request"},
                        {"Количество", "6", "1"}
                    })
            );
    }
}
