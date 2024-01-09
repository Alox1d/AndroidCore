package ru.alox1d.androidcore.multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SynchronisedListTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arrayList.add(i);
        }
        List<Integer> synchronisedList = Collections.synchronizedList(arrayList);

        Runnable iterateTask = () -> {
            synchronized (synchronisedList) {
                Iterator<Integer> iterator = synchronisedList.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
        };

        Runnable removeTask = () -> {
            synchronisedList.remove(10);
        };

        Thread thread1 = new Thread(iterateTask);
        Thread thread2 = new Thread(removeTask);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(synchronisedList);
    }
}
