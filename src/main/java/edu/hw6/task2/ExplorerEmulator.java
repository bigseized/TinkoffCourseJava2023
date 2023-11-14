package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExplorerEmulator {

    public static void cloneFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
            return;
        }
        if (!Files.exists(getNewPath(path))) {
            Files.createFile(getNewPath(path));
            return;
        }

        for (int i = 2; ; i++) {
            if (!Files.exists(getNewCopyName(path, i))) {
                Files.createFile(getNewCopyName(path, i));
                return;
            }
        }
    }

    private static Path getNewPath(Path path) {
        String oldName = path.toString().split("\\.")[0];
        String fileType = path.toString().split("\\.")[1];
        return Path.of(oldName + " - копия." + fileType);
    }

    private static Path getNewCopyName(Path path, int copyCount) {
        String oldName = path.toString().split("\\.")[0];
        String fileType = path.toString().split("\\.")[1];
        return Path.of(oldName + " - копия " + "(" + copyCount + ")" + "." + fileType);
    }

    public static void main(String[] args) throws IOException {
        cloneFile(Path.of(
            "C:\\Users\\1\\TinkoffCourseJava2023\\project-template\\src\\main\\java\\edu\\hw6\\task2\\her.txt"));
    }
}
