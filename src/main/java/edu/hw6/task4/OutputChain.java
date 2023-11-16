package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class OutputChain {

    private OutputChain() {
    }

    public static void chain() throws IOException {
        Path path = Path
            .of("src/main/java/edu/hw6/task4/newFile");
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Files.createFile(path);

        try (OutputStream outputStream = Files.newOutputStream(path)) {
            var checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
            var bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            var outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
            var printWriter = new PrintWriter(outputStreamWriter);
            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");
            printWriter.flush();
        }
    }
}
