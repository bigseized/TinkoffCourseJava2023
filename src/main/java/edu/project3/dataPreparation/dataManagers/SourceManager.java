package edu.project3.dataPreparation.dataManagers;

import edu.project3.dataPreparation.dataParsers.LocalPathParser;
import edu.project3.dataPreparation.dataParsers.LogsParser;
import edu.project3.dataPreparation.dataReceivers.HttpReceiver;
import edu.project3.dataPreparation.dataReceivers.LocalReceiver;
import edu.project3.types.ParsedLog;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import lombok.Getter;

@Getter
public class SourceManager {

    private List<String> paths;

    public ParsedLog[] getParsedLogs(String path) {
        List<String> logs;

        if (isValidURL(path)) {
            logs = HttpReceiver.loadLogs(path);
            paths.add(path);
        } else {
            try {
                List<String> parsedLocalPath = LocalPathParser.parseLocalPath(path);
                logs = LocalReceiver.loadLogs(parsedLocalPath);
                paths = parsedLocalPath;
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("Невозможно получить данные");
            }
        }

        if (logs == null) {
            throw new IllegalArgumentException("По указанному пути не найдено ни одного лога");
        }

        return LogsParser.getParsedLogs(logs);
    }

    private boolean isValidURL(String checkString) {
        try {
            URL url = new URL(checkString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
