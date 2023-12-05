package edu.project3.metricsTest;

import edu.project3.TestData;
import edu.project3.metrics.dataProviders.IpsUserCounterMetric;
import edu.project3.metrics.dataProviders.Metric;
import edu.project3.types.MetricsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IpsUserCounterMetricTest {
    @Test
    @DisplayName("One file IP metric build test")
    public void build_shouldReturnMetricForIpInfo() {
        Metric ipsMetric = new IpsUserCounterMetric();
        ipsMetric.createMetric(TestData.metricParams);

        assertThat(ipsMetric.getMetricsData())
            .usingRecursiveComparison()
            .isEqualTo(
            MetricsData.builder()
                .title(
                    "IP адреса")
                .data(new String[][] {
                    {"IP-адрес", "141.96.175.104", "165.138.198.30", "72.153.133.97",
                        "72.153.133.99",
                        "185.253.246.248", "11.71.87.42", "204.196.83.88"},
                    {"Количество", "1", "1", "1", "1", "1", "1", "1"}
                })
        );
    }
}
