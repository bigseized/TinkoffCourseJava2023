package edu.project3.metrics.dataProviders;

import edu.project3.types.MetricParams;
import edu.project3.types.MetricsData;
import edu.project3.types.ParsedLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IpsUserCounterMetric implements Metric {
    private MetricsData metricsData;

    public void createMetric(MetricParams data) {
        Map<String, Integer> map = Arrays.stream(data.getParsedData().getLogs()).map(ParsedLog::ip)
            .collect(Collectors.toMap(Function.identity(), e -> 1, Math::addExact));

        prepareMetric(map);
    }

    @Override
    public MetricsData getMetricsData() {
        return metricsData;
    }

    private void prepareMetric(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<String> values = new ArrayList<>(list.stream().map(entry -> String.valueOf(entry.getValue())).toList());
        List<String> keys = new ArrayList<>(list.stream().map(Map.Entry::getKey).toList());

        values.add(0, "Количество");
        keys.add(0, "IP-адрес");

        metricsData = MetricsData.builder()
            .title("IP адреса")
            .data(new String[][] {keys.toArray(new String[0]), values.toArray(new String[0])})
            .build();
    }
}
