package edu.project3.dataPreparation.dataManagers;

import edu.project3.dataPreparation.dataParsers.LogsParser;
import edu.project3.dataPreparation.dataReceivers.AbstractReceiver;
import edu.project3.dataPreparation.dataReceivers.HttpReceiver;
import edu.project3.dataPreparation.dataReceivers.LocalReceiver;
import edu.project3.types.ParsedLog;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public class SourceManager {

    private List<String> paths = new ArrayList<>();

    public ParsedLog[] getParsedLogs(String path) {
        Stream<String> logs;

        AbstractReceiver abstractReceiver = AbstractReceiver.makeChain(
            new HttpReceiver(),
            new LocalReceiver()
        );

        logs = abstractReceiver.get(path).orElseThrow(() -> new RuntimeException("Неудалось получить данные"));
        paths = abstractReceiver.getUsedPaths();

        if (logs == null) {
            throw new IllegalArgumentException("По указанному пути не найдено ни одного лога");
        }

        return LogsParser.getParsedLogs(logs);
    }
}
