package ru.alox1d.androidcore.multithreading;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(4);

        //Producer
        new Thread(() -> {
            Integer i = 0;
            try {
                while (true) {
                    Thread.sleep(3000);
                    arrayBlockingQueue.put(++i);
                    System.out.println("Producer put : " + i + ", queue : " + arrayBlockingQueue);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        //Consumer
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(9000);
                    Integer j = arrayBlockingQueue.take();
                    System.out.println("Consumer take : " + j + ", queue : " + arrayBlockingQueue);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
