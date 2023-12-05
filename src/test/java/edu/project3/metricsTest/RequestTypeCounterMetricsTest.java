package edu.project3.metricsTest;

import edu.project3.TestData;
import edu.project3.metrics.dataProviders.Metric;
import edu.project3.metrics.dataProviders.RequestTypeCounterMetrics;
import edu.project3.types.MetricsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestTypeCounterMetricsTest {
    @Test
    @DisplayName("One file request metric build test")
    public void build_shouldReturnMetricForResourcesInfo() {
        Metric requestTypeCounterMetrics = new RequestTypeCounterMetrics();
        requestTypeCounterMetrics.createMetric(TestData.metricParams);
        assertThat(requestTypeCounterMetrics.getMetricsData())
            .usingRecursiveComparison()
            .ignoringCollectionOrderInFields()
            .isEqualTo(
                MetricsData.builder()
                    .title(
                        "Типы запросов")
                    .data(new String[][] {
                        {"Код", "GET", "PUT"},
                        {"Количество", "6", "1"}
                    })
            );
    }
}
