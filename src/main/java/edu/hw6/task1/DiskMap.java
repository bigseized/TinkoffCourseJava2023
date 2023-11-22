package edu.hw6.task1;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class DiskMap implements Map<String, String> {
    private final static String FILE_READ_ERROR = "Ошибка при чтении файла";
    private final Path filePath;

    @SneakyThrows
    public DiskMap(Path path) {
        filePath = path;
        try {
            if (Files.exists(filePath)) {
                deleteFile();
            }
            Files.createFile(filePath);
            try (FileChannel fileChannel = FileChannel.open(path, WRITE, READ)) {
                FileLock fileLock = fileChannel.lock();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании/удалении файла", e);
        }
    }

    @Override
    @SneakyThrows
    public int size() {
        return Files.readAllLines(filePath).size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            return Files.lines(filePath).anyMatch(line -> line.split(":")[0].equals(key));
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        try {
            return Files.lines(filePath).anyMatch(line -> line.split(":")[1].equals(value));
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR, e);
        }
    }

    @Override
    public String get(Object key) {
        try {
            String ans = Files
                .lines(filePath).filter(line -> line.split(":")[0].equals(key))
                .findFirst().orElse(null);
            if (ans != null) {
                return ans.split(":")[1];
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR, e);
        }
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        try {
            Files.write(filePath, (key + ":" + value + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Невозможно записать в файл", e);
        }
        return (key + ":" + value + "\n");
    }

    @Override
    public String remove(Object key) {
        String value = get(key);
        try {
            Files.write(filePath, Files
                .lines(filePath)
                .filter(line -> !line.split(":")[0].equals(key))
                .toList(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Невозможо удалить запись", e);
        }
        return value;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    @SneakyThrows
    public void clear() {
        Files.write(filePath, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        try {
            return Files
                .lines(filePath).map(line -> line.split(":")[0])
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR, e);
        }
    }

    @NotNull
    @Override
    public Collection<String> values() {
        try {
            return Files
                .lines(filePath).map(line -> line.split(":")[1])
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR, e);
        }
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        try {
            return Files
                .lines(filePath).map(line -> new AbstractMap.SimpleEntry<>(line.split(":")[0], line.split(":")[1]))
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR, e);
        }
    }

    private void deleteFile() throws IOException {
        Files.delete(filePath);
    }
}
