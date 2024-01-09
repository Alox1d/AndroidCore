package ru.alox1d.androidcore.multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        new Person("Дима", semaphore);
        new Person("Саша", semaphore);
        new Person("Алеша", semaphore);
        new Person("Елена", semaphore);
        new Person("Аким", semaphore);
    }
}

class Person extends Thread {
    private String name;
    private Semaphore semaphore;

    public Person(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
        start();
    }

    @Override
    public void run() {
        System.out.println(name + " ждет возможность занять телефонную будку");
        try {
            semaphore.acquire();
            System.out.println(name + " говорит по телефону");
            sleep(2000);
            System.out.println(name + " закончил(а) разговор");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
