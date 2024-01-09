package ru.alox1d.androidcore.multithreading;

public class VolatileTest extends Thread {
    private volatile boolean b = true;

    @Override
    public void run() {
        long counter = 0;
        while (b) {
            counter++;
        }
        System.out.println("run() is finished, counter = " + counter);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest thread = new VolatileTest();
        thread.start();
        sleep(2000);
        System.out.println("After 2 seconds it is time to wake up!");
        thread.b = false;
        thread.join();
        System.out.println("main() is finished");
    }
}
