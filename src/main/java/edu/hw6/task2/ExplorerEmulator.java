package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("MultipleStringLiterals")
public class ExplorerEmulator {

    private final static String EXCEPTION_MESSAGE = "Невозможно создать файл";

    private ExplorerEmulator() {
    }

    public static void cloneFile(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(EXCEPTION_MESSAGE);
            }
        } else if (!Files.exists(getNewPath(path))) {
            try {
                Files.createFile(getNewPath(path));
            } catch (IOException e) {
                throw new RuntimeException(EXCEPTION_MESSAGE);
            }
        } else {
            for (int i = 2; ; i++) {
                if (!Files.exists(getNewCopyName(path, i))) {
                    try {
                        Files.createFile(getNewCopyName(path, i));
                    } catch (IOException e) {
                        throw new RuntimeException(EXCEPTION_MESSAGE);
                    }
                    return;
                }
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
}
