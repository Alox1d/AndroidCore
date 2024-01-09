package ru.alox1d.androidcore.multithreading;

public class DaemonTest {
    public static void main(String[] args) {
        System.out.println("Main thread starts");
        Thread userThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is daemon: " +
                        Thread.currentThread().isDaemon());
                for (char c = 'A'; c <= 'J'; c++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(c);
                }
            }
        });

        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is daemon: " +
                        Thread.currentThread().isDaemon());
                for (int i = 1; i <= 1000; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(i);
                }
            }
        });

        userThread.setName("user_thread");
        daemonThread.setName("daemon_thread");
        daemonThread.setDaemon(true);
        userThread.start();
        daemonThread.start();

        System.out.println("Main thread ends");
    }
}
