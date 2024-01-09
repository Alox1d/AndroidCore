package ru.alox1d.androidcore.io_nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReadWriteTest {
    public static void main(String[] args) {
        String garik = "Весьма порой мешает мне заснуть\n" +
                "Волнующая, как ни поверни,\n" +
                "Открывшаяся мне внезапно суть\n" +
                "Какой-нибудь немыслимой херни.";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("I_Guberman.txt"))) {
            writer.write(garik);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("I_Guberman.txt"))) {
            String s = "";
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
