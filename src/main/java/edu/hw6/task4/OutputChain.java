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

    public static void chain() throws IOException {
        Path path = Path
            .of("C:\\Users\\1\\TinkoffCourseJava2023\\project-template\\src\\main\\java\\edu\\hw6\\task4\\newFile");
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Files.createFile(path);

        try (OutputStream outputStream = Files.newOutputStream(path)) {
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter);
            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");
            printWriter.flush();
        }
    }
}
