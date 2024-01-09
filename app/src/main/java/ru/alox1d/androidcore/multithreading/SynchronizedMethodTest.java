package ru.alox1d.androidcore.multithreading;

public class SynchronizedMethodTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        Thread thread3 = new Thread(new MyRunnable());
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Counter {
    public static int count;

    public synchronized static void increment() {
        count++;
        System.out.print(count + " ");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            Counter.increment();
        }
    }
}
