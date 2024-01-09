package ru.alox1d.androidcore.multithreading;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread starts");
        Thread interruptedThread = new InterruptedThread();
        interruptedThread.start();
        Thread.sleep(2000);
        interruptedThread.interrupt();
        interruptedThread.join();
        System.out.println("Main thread ends");
    }
}

class InterruptedThread extends Thread {
    @Override
    public void run() {
        double sum = 0;
        for (int i = 0; i < 1000; i++) {
            if (isInterrupted()) {
                System.out.println("Поток хотят прервать. Завершаем работу.");
                System.out.println(sum);
                return;
            }
            sum += Math.sqrt(i);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Поток хотят прервать во время сна. Завершаем работу.");
                System.out.println(sum);
                return;
            }
        }
        System.out.println(sum);
    }
}