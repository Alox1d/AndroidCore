package ru.alox1d.androidcore.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Increment();
        Thread thread2 = new Increment();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(counter);
    }
}

class Increment extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            AtomicIntegerTest.counter.incrementAndGet();
        }
    }
}
