package edu.project3.dataPreparation.dataParsers;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.List;

public class LocalPathParser {

    private LocalPathParser() {
    }

    public static List<String> parseLocalPath(String path) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**" + path);

        try (var filesStream =
                 Files.find(
                     Paths.get(""),
                     Integer.MAX_VALUE,
                     ((filePath, basicFileAttributes) -> !basicFileAttributes.isDirectory()
                         && pathMatcher.matches(filePath)), FileVisitOption.values()
                 )) {
            return filesStream.map(Path::toString).toList();
        } catch (IOException ex) {
            throw new RuntimeException("Не возможно считать файл");
        }
    }
}
