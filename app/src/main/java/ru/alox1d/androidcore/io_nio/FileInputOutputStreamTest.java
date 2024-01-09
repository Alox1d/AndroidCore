package ru.alox1d.androidcore.io_nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStreamTest {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("C:\\Users\\ZIP-PDA\\Desktop\\Java_logo.png");
             FileOutputStream outputStream = new FileOutputStream("C:\\Users\\ZIP-PDA\\Desktop\\Java_copy.png")) {
            int b;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
