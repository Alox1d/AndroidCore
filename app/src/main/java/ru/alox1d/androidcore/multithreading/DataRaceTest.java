package ru.alox1d.androidcore.multithreading;

public class DataRaceTest {
    private volatile static long x = -1;


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyRun1());
        Thread thread2 = new Thread(new MyRun2());
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("x = " + x);
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);

        System.out.println("main() is finished");
    }

    static class MyRun1 implements Runnable {

        @Override
        public void run() {
            x = 0L;
        }
    }

    static class MyRun2 implements Runnable {

        @Override
        public void run() {
            x = Long.MAX_VALUE;
        }
    }
}
