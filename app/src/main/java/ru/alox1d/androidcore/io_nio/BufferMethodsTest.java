package ru.alox1d.androidcore.io_nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferMethodsTest {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("ABC.txt", "rw");
             FileChannel channel = file.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(5);
            channel.read(buffer);
            buffer.flip();
            System.out.println((char) buffer.get());
            buffer.rewind(); //позволяет начать читать буфер сначала, возвращает position на 0
            System.out.println((char) buffer.get());
            System.out.println("-----------------------------");

            System.out.println((char) buffer.get());
            buffer.compact();
            System.out.println("Вызван compact()");
            channel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            System.out.println("-----------------------------");

            buffer.clear();
            channel.read(buffer);
            buffer.flip();
            System.out.println((char) buffer.get());
            buffer.mark();
            System.out.println((char) buffer.get());
            buffer.reset();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
