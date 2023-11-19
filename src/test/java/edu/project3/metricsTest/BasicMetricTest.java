package edu.project3.metricsTest;

import edu.project3.TestData;
import edu.project3.metrics.dataProviders.BasicMetric;
import edu.project3.metrics.dataProviders.Metric;
import edu.project3.types.MetricsData;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicMetricTest {

    @Test
    @DisplayName("One file main metric build test")
    public void build_shouldReturnMetricForMainInfo() {
        Metric basicMetric = new BasicMetric();
        basicMetric.createMetric(TestData.metricParams);

        assertThat(basicMetric.getMetricsData())
            .usingRecursiveComparison()
            .isEqualTo(
                MetricsData.builder()
                    .title(
                        "Общая информация")
                    .data(new String[][] {
                        {"Метрика", "Файл(-ы)", "Начальная дата", "Конечная дата", "Количество запросов",
                            "Средний размер ответа"},
                        {"Значение", "`test`", "-", "-", "7", "1779b"}
                    })
            );
    }
}
