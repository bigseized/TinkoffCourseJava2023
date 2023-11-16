package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class DirectoryStreamFilters {
    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;
    public static final int DOT_CODE = 0x89;

    public static DirectoryStream.Filter<Path> filter = magicNumber(DOT_CODE, 'P', 'N', 'G');

    public static AbstractFilter largerThan(int size) {
        return (path -> Files.size(path) > size);
    }

    private DirectoryStreamFilters() {
    }


    public static AbstractFilter magicNumber(int... magicNumbers) {
        return path -> {
            byte[] bytes = Files.readAllBytes(path);
            if (bytes.length < magicNumbers.length) {
                return false;
            }
            for (int j = 0; j < magicNumbers.length; j++) {
                if (bytes[j] != (byte) magicNumbers[j]) {
                    return false;
                }
            }
            return true;
        };
    }

    public static AbstractFilter typeMatches(String type) {
        return (path -> path.getFileName().toString().split("\\.")[1].equals(type));
    }

    public static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return (path -> pattern.matcher(path.getFileName().toString()).find());
    }
}
