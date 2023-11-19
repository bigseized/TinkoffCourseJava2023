package edu.project3.dataPreparation.dataReceivers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalReceiver {

    public static List<String> loadLogs(List<String> paths) throws FileNotFoundException {
        List<String> logs = new ArrayList<>();
        for (var currentPath : paths) {
            Path filePath = Path.of(currentPath);
            if (Files.exists(filePath)) {
                try (var filesStream = Files.lines(filePath)) {
                    logs.addAll(filesStream.toList());
                } catch (IOException e) {
                    throw new RuntimeException("Невозможно считать данные из файла");
                }
            } else {
                throw new FileNotFoundException("Файл не найден");
            }
        }
        return logs;

    }
}
