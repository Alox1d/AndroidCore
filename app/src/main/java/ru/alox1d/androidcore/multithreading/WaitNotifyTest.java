package ru.alox1d.androidcore.multithreading;

public class WaitNotifyTest {
    public static void main(String[] args) {
        Market market = new Market();
        Producer producer = new Producer(market);
        Consumer consumer = new Consumer(market);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}

class Market {
    private int breadCount;

    public synchronized void getBread() {
        while (breadCount < 1) {
            System.out.println("Потребитель ожидает хлеб");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        breadCount--;
        System.out.println("Потребитель забрал 1 хлеб");
        System.out.println("Хлеба в магазине: " + breadCount);
        notify();
    }

    public synchronized void putBread() {
        while (breadCount >= 5) {
            System.out.println("Магазин заполнен, производитель ожидает");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        breadCount++;
        System.out.println("Производитель добавил в магазин 1 хлеб");
        System.out.println("Хлеба в магазине: " + breadCount);
        notify();
    }
}

class Producer implements Runnable {
    private final Market market;

    public Producer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            market.putBread();
        }
    }
}

class Consumer implements Runnable {
    private final Market market;

    public Consumer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            market.getBread();
        }
    }
}
