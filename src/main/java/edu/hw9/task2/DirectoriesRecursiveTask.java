package edu.hw9.task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class DirectoriesRecursiveTask extends RecursiveTask<List<Path>> {
    private final Path rootDir;
    private final Integer minimumFilesNumber;

    @Override
    @SneakyThrows
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        List<ForkJoinTask<List<Path>>> subTasks = new ArrayList<>();
        int counterOfFiles = 0;
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(rootDir)) {
            for (var path : paths) {
                if (!Files.isDirectory(path)) {
                    counterOfFiles++;
                    continue;
                }
                DirectoriesRecursiveTask newTask = new DirectoriesRecursiveTask(path, minimumFilesNumber);
                subTasks.add(newTask.fork());
            }
        }
        if (counterOfFiles >= minimumFilesNumber) {
            result.add(rootDir);
        }
        subTasks.stream().flatMap(task -> task.join().stream()).forEach(result::add);
        return result;
    }
}
