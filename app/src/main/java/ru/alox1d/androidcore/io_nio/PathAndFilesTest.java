package ru.alox1d.androidcore.io_nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PathAndFilesTest {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("test1.txt");
        Path folderPath = Paths.get("D:\\A");
        Files.createFile(filePath);

        String dialog = "-Privet!\n-Salut!\n-Kak dela?\n-Normalno";
        Files.write(filePath, dialog.getBytes());

        Files.copy(filePath, folderPath.resolve("test1_copy.txt"));
        Files.move(filePath, folderPath.resolve("test1.txt"));
        Files.delete(Path.of("D:\\A\\test1_copy.txt"));

        List<String> strings = Files.readAllLines(folderPath.resolve(filePath));
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
