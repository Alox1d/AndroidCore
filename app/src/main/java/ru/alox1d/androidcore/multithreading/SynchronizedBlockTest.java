package ru.alox1d.androidcore.multithreading;

public class SynchronizedBlockTest {
    private final static Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MobileCaller(lock));
        Thread thread2 = new Thread(new SkypeCaller(lock));
        Thread thread3 = new Thread(new WhatsappCaller(lock));

        thread1.start();
        thread2.start();
        thread3.start();
    }

}

class MobileCaller implements Runnable {
    private final Object lock;

    public MobileCaller(Object lock) {
        this.lock = lock;
    }

    public void mobileCall() {
        synchronized (lock) {
            System.out.println("Mobile call starts");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Mobile call ends");
        }
    }

    @Override
    public void run() {
        mobileCall();
    }
}

class SkypeCaller implements Runnable {
    private final Object lock;

    public SkypeCaller(Object lock) {
        this.lock = lock;
    }

    public void skypeCall() {
        synchronized (lock) {
            System.out.println("Skype call starts");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Skype call ends");
        }
    }

    @Override
    public void run() {
        skypeCall();
    }
}

class WhatsappCaller implements Runnable {
    private final Object lock;

    public WhatsappCaller(Object lock) {
        this.lock = lock;
    }

    public void whatsappCall() {
        synchronized (lock) {
            System.out.println("WhatsApp call starts");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("WhatsApp call ends");
        }
    }

    @Override
    public void run() {
        whatsappCall();
    }
}
