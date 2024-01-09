package ru.alox1d.androidcore.multithreading;

public class DataRaceStaticTest {
    private static long x = 0;


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyRun1());
        Thread thread2 = new Thread(new MyRun2());
        thread2.start();
        thread1.start();


        thread1.join();
        thread2.join();
        System.out.println("x = " + x);
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);

        System.out.println("main() is finished");
    }

    static class MyRun1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                x++;
            }
        }
    }

    static class MyRun2 implements Runnable {

        @Override
        public void run() {
            while (x < 5) {

            }
            System.out.println(x);
        }
    }
}
