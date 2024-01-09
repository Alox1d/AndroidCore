package ru.alox1d.androidcore.generics;

import java.util.ArrayList;

public class FruitsCovariance {
    static class Fruit {
        int weight;
    }

    static class Citrus extends Fruit {
        int weight;
    }

    static class Orange extends Citrus {

    }

    private static int totalWeight(ArrayList<? extends Citrus> oranges) {
        int weight = 0;
        for (Citrus orange : oranges) {
            weight += orange.weight;
        }
        // Возможно
        Fruit fruit = oranges.get(0);
//        Object object = oranges.get(0);

        return weight;
    }

    private static void addOrange(ArrayList<? super Citrus> oranges) {
        int weight = 0;
        oranges.add(new Orange());

        // Возможно
        Object object = oranges.get(0);
    }

    public static void main(String[] args) {
        ArrayList<Orange> oranges = new ArrayList<>();
        Orange o = new Orange();
        o.weight = 1;
        oranges.add(o);

        int w = totalWeight(oranges);
        System.out.println(w);

        ArrayList<Fruit> fruits = new ArrayList<>();
        addOrange(fruits);
    }
}

