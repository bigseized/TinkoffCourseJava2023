package edu.project3.dataPreparation.dataReceivers;

import edu.project3.dataPreparation.dataParsers.LocalPathParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.SneakyThrows;

public class LocalReceiver extends AbstractReceiver {

    @SneakyThrows public Stream<String> loadLogs(List<String> paths) {
        return paths.stream()
            .map(Path::of)
            .filter(Files::exists)
            .flatMap(path -> {
                try {
                    return Files.lines(path);
                } catch (IOException e) {
                    throw new RuntimeException("Невозможно считать данные из файла " + path, e);
                }
            });
    }

    @Override
    public Optional<Stream<String>> get(String path) {
        List<String> paths = LocalPathParser.parseLocalPath(path);
        if (!paths.isEmpty()) {
            super.paths = paths;
            return Optional.of(loadLogs(paths));
        }
        return getNext(path);
    }
}
