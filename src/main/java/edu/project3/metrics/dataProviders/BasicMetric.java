package edu.project3.metrics.dataProviders;

import edu.project3.types.MetricParams;
import edu.project3.types.MetricsData;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class BasicMetric implements Metric {
    private MetricsData metricsData;

    public void createMetric(MetricParams data) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Метрика", "Значение");
        map.put("Файл(-ы)", "`" + String.join(", ", data.getPaths()) + "`");
        map.put("Начальная дата", data.getRawArgs().getStartDate() == null ? "-" : data.getRawArgs().getStartDate());
        map.put("Конечная дата", data.getRawArgs().getEndDate() == null ? "-" : data.getRawArgs().getEndDate());
        int numberOfRequests = data.getParsedData().getLogs().length;
        map.put("Количество запросов", String.valueOf(numberOfRequests));
        map.put("Средний размер ответа", Arrays.stream(data.getParsedData()
                .getLogs())
            .map(entry -> entry.response().numberOfBytes())
            .mapToLong(e -> e).sum() / numberOfRequests + "b");
        prepareMetric(map);
    }

    @Override
    public MetricsData getMetricsData() {
        return metricsData;
    }

    private void prepareMetric(Map<String, String> map) {
        String[] firstColumn = map.keySet().toArray(new String[0]);
        String[] secondColumn = map.values().toArray(new String[0]);

        metricsData = MetricsData.builder()
            .title("Общая информация")
            .data(new String[][] {firstColumn, secondColumn})
            .build();
    }
}
