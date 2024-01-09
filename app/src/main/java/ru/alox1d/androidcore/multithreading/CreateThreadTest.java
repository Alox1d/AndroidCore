package ru.alox1d.androidcore.multithreading;

public class CreateThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread1 = new A();
        myThread1.setName("Поток #1");
        myThread1.setPriority(Thread.MAX_PRIORITY);
        myThread1.start();

        Thread myThread2 = new Thread(new B());
        myThread2.setName("Поток #2");
        myThread2.setPriority(Thread.MIN_PRIORITY);
        myThread2.start();

        myThread1.join();
        myThread2.join();
        System.out.println("Конец метода main()");
    }
}

class A extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(currentThread().getName() + ", i = " + i);
        }
    }
}

class B implements Runnable {
    @Override
    public void run() {
        for (int i = 9; i >= 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ", i = " + i);
        }
    }
}
