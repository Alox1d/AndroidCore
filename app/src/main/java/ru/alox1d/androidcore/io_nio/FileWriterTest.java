package ru.alox1d.androidcore.io_nio;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
    public static void main(String[] args) throws IOException {
        String poem = "Ах, если мученик любви\n" +
                "Страдает страстью безнадёжно,\n" +
                "Хоть грустно жить, друзья мои,\n" +
                "Однако жить ещё возможно.\n";
        String author = "А.С. Пушкин";

        FileWriter writer = null;
        try {
            writer = new FileWriter("Ruslan_and_Ludmila.txt", true);
            writer.write(poem);
            writer.write(author);
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
