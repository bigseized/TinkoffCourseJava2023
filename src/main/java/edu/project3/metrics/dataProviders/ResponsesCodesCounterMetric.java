package edu.project3.metrics.dataProviders;

import edu.project3.types.MetricParams;
import edu.project3.types.MetricsData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.http.impl.EnglishReasonPhraseCatalog;

public class ResponsesCodesCounterMetric implements Metric {

    private MetricsData metricsData;

    public void createMetric(MetricParams data) {
        Map<Integer, Integer> map =
            Arrays.stream(data.getParsedData().getLogs()).map(entry -> entry.response().statusCode())
                .collect(Collectors.toMap(Function.identity(), e -> 1, Math::addExact));

        printMetric(map);
    }

    @Override
    public MetricsData getMetricsData() {
        return metricsData;
    }

    private void printMetric(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<String> values = new ArrayList<>(list.stream().map(entry -> String.valueOf(entry.getValue())).toList());
        List<String> keys = new ArrayList<>(list.stream().map(entry -> String.valueOf(entry.getKey())).toList());
        List<String> messages = new ArrayList<>(list.stream().map(entry -> getPhrase(entry.getKey())).toList());

        values.add(0, "Количество");
        keys.add(0, "Код");
        messages.add(0, "Имя");

        metricsData = MetricsData.builder()
            .title("Коды ответа")
            .data(new String[][] {keys.toArray(new String[0]),
                messages.toArray(new String[0]),
                values.toArray(new String[0])})
            .build();
    }

    public String getPhrase(int statusCode) {
        return EnglishReasonPhraseCatalog.INSTANCE.getReason(statusCode, Locale.ENGLISH);
    }
}
