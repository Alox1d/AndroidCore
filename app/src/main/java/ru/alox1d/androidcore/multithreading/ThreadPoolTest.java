package ru.alox1d.androidcore.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main поток начал работу");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();
        // На выполнение 10 тасок со sleep(500) 5 потокам понадобится примерно 1 сек
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Main поток закончил работу");
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " начал работу");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " закончил работу");
    }
}
