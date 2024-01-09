package ru.alox1d.androidcore.io_nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelBufferTest {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("quotes.txt", "rw");
             FileChannel channel = file.getChannel()
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(25);
            StringBuilder text = new StringBuilder();
            int byteRead = channel.read(buffer);
            while (byteRead > 0) {
                System.out.println("byteRead: " + byteRead);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    text.append((char) buffer.get());
                }
                buffer.clear();
                byteRead = channel.read(buffer);
            }
            System.out.println(text);

            String quote = "\n\"We must run as fast as we can, just to stay in place. " +
                    "And if you wish to go anywhere you must run twice as fast as that.\"";
            ByteBuffer buffer1 = ByteBuffer.wrap(quote.getBytes());
            channel.write(buffer1);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
