package edu.hw9.task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class FilesRecursiveTask extends RecursiveTask<List<Path>> {
    private final Path rootDir;
    private final Predicate<Path> predicate;

    @Override
    @SneakyThrows
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        List<ForkJoinTask<List<Path>>> subTasks = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(rootDir)) {
            for (var path : paths) {
                if (!Files.isDirectory(path)) {
                    if (predicate.test(path)) {
                        result.add(path);
                    }
                    continue;
                }
                FilesRecursiveTask newTask = new FilesRecursiveTask(path, predicate);
                subTasks.add(newTask.fork());
            }
        }

        subTasks.stream().flatMap(task -> task.join().stream()).forEach(result::add);
        return result;
    }
}
