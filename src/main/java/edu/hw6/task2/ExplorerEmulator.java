package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("MultipleStringLiterals")
public class ExplorerEmulator {

    private final static String EXCEPTION_MESSAGE = "Невозможно создать файл";
    private final static String NAME_TYPE_DELIMITER = "\\.";

    private ExplorerEmulator() {
    }

    public static void cloneFile(Path path) {
        for (int i = 0; ; i++) {
            if (!Files.exists(getNewCopyName(path, i))) {
                try {
                    Files.createFile(getNewCopyName(path, i));
                } catch (IOException e) {
                    throw new RuntimeException(EXCEPTION_MESSAGE, e);
                }
                return;
            }
        }
    }

    private static Path getNewCopyName(Path path, int copyCount) {
        String oldName = path.toString().split(NAME_TYPE_DELIMITER)[0];
        String fileType = path.toString().split(NAME_TYPE_DELIMITER)[1];
        return switch (copyCount) {
            case 0 -> path;
            case 1 -> Path.of(oldName + " - копия." + fileType);
            default -> Path.of(oldName + " - копия " + "(" + copyCount + ")" + "." + fileType);
        };
    }
}
