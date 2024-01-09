package ru.alox1d.androidcore.io_nio;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("test1.txt");
        File folder = new File("D:\\A");
        File folder2 = new File("D:\\B");

        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("folder.getAbsolutePath() = " + folder.getAbsolutePath());
        System.out.println("-------------------------------------------");

        System.out.println("file.isAbsolute() = " + file.isAbsolute());
        System.out.println("folder.isAbsolute() = " + folder.isAbsolute());
        System.out.println("-------------------------------------------");

        System.out.println("file.isDirectory() = " + file.isDirectory());
        System.out.println("folder.isDirectory() = " + folder.isDirectory());
        System.out.println("-------------------------------------------");

        System.out.println("file.exists() = " + file.exists());
        System.out.println("folder.exists() = " + folder.exists());
        System.out.println("folder2.exists() = " + folder2.exists());
        System.out.println("-------------------------------------------");

        System.out.println("file.createNewFile() = " + file.createNewFile());
        System.out.println("folder.mkdir() = " + folder.mkdir());
        System.out.println("folder2.mkdir() = " + folder2.mkdir());
        System.out.println("-------------------------------------------");

        System.out.println("file.length() = " + file.length());
        System.out.println("folder.length() = " + folder.length());
        System.out.println("-------------------------------------------");

        System.out.println("file.delete() = " + file.delete());
        System.out.println("folder2.delete() = " + folder2.delete());
        System.out.println("-------------------------------------------");

        File[] files = folder.listFiles();
        System.out.println("folder.listFiles() = " + Arrays.toString(files));
        System.out.println("-------------------------------------------");
    }


}
