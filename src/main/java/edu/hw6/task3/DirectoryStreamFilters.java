package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class DirectoryStreamFilters {
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;

    public static DirectoryStream.Filter<Path> filter = magicNumber(0x89, 'P', 'N', 'G');
    private static AbstractFilter largerThan(int size) {
        return (path -> Files.size(path) > size);
    }

    private static AbstractFilter magicNumber(int... magicNumbers) {
        return path -> {
            byte[] bytes = Files.readAllBytes(path);
            if (bytes.length < magicNumbers.length)
                return false;
            for (int j = 0; j < magicNumbers.length; j++) {
                if (bytes[j] != (byte) magicNumbers[j]) {
                    return false;
                }
            }
            return true;
        };
    }

    private static AbstractFilter typeMatches(String type) {
        return (path -> path.getFileName().toString().split("\\.")[1].equals(type));
    }

    private static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return (path -> pattern.matcher(path.getFileName().toString()).find());
    }

    public static void main(String[] args) throws IOException {
        Path path = Path
            .of("C:\\Users\\1\\TinkoffCourseJava2023\\project-template\\src\\main\\java\\edu\\hw6\\task2");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(path, filter)) {
            entries.forEach(System.out::println);
        }
    }
}
