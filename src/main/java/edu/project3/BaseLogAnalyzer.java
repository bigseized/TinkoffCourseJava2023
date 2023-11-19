package edu.project3;

import edu.project3.dataPreparation.dataManagers.BaseDataManager;
import edu.project3.metrics.BaseMetricsManager;
import edu.project3.types.MetricParams;
import edu.project3.types.ParsedData;

public class BaseLogAnalyzer {

    private final String[] arguments;
    private final BaseDataManager baseDataManager = new BaseDataManager();

    public BaseLogAnalyzer(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Неверный формат ввода");
        }
        arguments = args;
    }

    public void run() {
        ParsedData parsedData = baseDataManager.getData(arguments);
        MetricParams metricParams =
            new MetricParams(parsedData, baseDataManager.getRawargs(), baseDataManager.getPaths());
        BaseMetricsManager metricsManager = new BaseMetricsManager();
        metricsManager.createMetrics(metricParams);
    }
}
