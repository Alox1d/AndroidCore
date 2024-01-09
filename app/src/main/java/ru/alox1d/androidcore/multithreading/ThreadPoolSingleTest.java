package ru.alox1d.androidcore.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolSingleTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main поток начал работу");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();
        System.out.println("Main поток закончил работу");
    }
}

class TaskSingle implements Runnable {
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
