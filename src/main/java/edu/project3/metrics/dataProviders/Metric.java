package edu.project3.metrics.dataProviders;

import edu.project3.types.MetricParams;
import edu.project3.types.MetricsData;

public interface Metric {
    void createMetric(MetricParams metricParams);

    MetricsData getMetricsData();
}
