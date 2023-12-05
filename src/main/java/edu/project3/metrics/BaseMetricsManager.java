package edu.project3.metrics;

import edu.project3.metrics.dataProviders.BasicMetric;
import edu.project3.metrics.dataProviders.IpsUserCounterMetric;
import edu.project3.metrics.dataProviders.Metric;
import edu.project3.metrics.dataProviders.RequestCounterMetric;
import edu.project3.metrics.dataProviders.RequestTypeCounterMetrics;
import edu.project3.metrics.dataProviders.ResponsesCodesCounterMetric;
import edu.project3.metrics.renderers.Renderer;
import edu.project3.types.Format;
import edu.project3.types.MetricParams;
import java.util.ArrayList;
import java.util.List;

public class BaseMetricsManager {

    private final MetricsRenderersFactory metricsRenderersFactory = new MetricsRenderersFactory();

    public void createMetrics(MetricParams metricParams) {
        List<Metric> metrics = getMetrics();
        for (var metric : metrics) {
            metric.createMetric(metricParams);
        }

        List<String> renderedStrings = renderMetrics(metrics, metricParams.getParsedData().getFormat());
        printMetrics(renderedStrings);
    }

    private List<Metric> getMetrics() {
        List<Metric> metrics = new ArrayList<>();
        metrics.add(new BasicMetric());
        metrics.add(new RequestCounterMetric());
        metrics.add(new RequestTypeCounterMetrics());
        metrics.add(new ResponsesCodesCounterMetric());
        metrics.add(new IpsUserCounterMetric());
        return metrics;
    }

    private List<String> renderMetrics(List<Metric> metrics, Format format) {
        Renderer renderer = metricsRenderersFactory.getRenderer(format);
        List<String> renderedStrings = new ArrayList<>();
        for (var metric : metrics) {
            renderedStrings.add(renderer.render(metric.getMetricsData()));
        }
        return renderedStrings;
    }

    private void printMetrics(List<String> renderedMetrics) {
        renderedMetrics.forEach(System.out::println);
    }
}
