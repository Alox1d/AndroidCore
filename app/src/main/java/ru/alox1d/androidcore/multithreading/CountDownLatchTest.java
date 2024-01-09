package ru.alox1d.androidcore.multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) {
        new Runner("Брент Фиковски", countDownLatch);
        new Runner("Мэтт Фрейзер", countDownLatch);
        new Runner("Рич Фронинг", countDownLatch);

        three();
        two();
        one();
    }

    private static void three() {
        System.out.println(countDownLatch.getCount() + ": на старт!");
        countDownLatch.countDown();
    }

    private static void two() {
        System.out.println(countDownLatch.getCount() + ": внимание!");
        countDownLatch.countDown();
    }

    private static void one() {
        System.out.println(countDownLatch.getCount() + ": марш!");
        countDownLatch.countDown();
    }
}

class Runner extends Thread {
    private String name;
    private CountDownLatch countDownLatch;

    public Runner(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
        start();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + " побежал!");
    }
}
