package edu.hw9.task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParallelSearchTree {
    private static final ForkJoinPool POOL = ForkJoinPool.commonPool();

    public static List<Path> findLargeDirectories(Path rootDir, Integer minimumFiles) {
        List<Path> result;
        result = POOL.invoke(new DirectoriesRecursiveTask(rootDir, minimumFiles));
        POOL.shutdown();
        return result;
    }

    public static List<Path> findFilesWithPredicate(Path rootDir, Predicate<Path> predicate) {
        List<Path> result;
        result = POOL.invoke(new FilesRecursiveTask(rootDir, predicate));
        POOL.shutdown();
        return result;
    }

}
