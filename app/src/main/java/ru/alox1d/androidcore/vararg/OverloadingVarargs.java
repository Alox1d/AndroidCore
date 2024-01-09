package ru.alox1d.androidcore.vararg;

public class OverloadingVarargs {
    static void f(Character... args) {
        System.out.print("first");
        for (Character с : args)
            System.out.print(" " + с);
        System.out.println();
    }

    static void f(Integer... args) {
        System.out.print("second");
        for (Integer i : args)
            System.out.print(" " + i);
        System.out.println();
    }

    static void f(Long... args) {
        System.out.println("third");
    }

//    static void f(int i, Long... args) {
//        System.out.println("third");
//    }

    public static void main(String[] args) {
        Character c = 99;
        f('a', 'b', c);
        f(1);
        f(2, 1);
        f(0);
        f(0L);
//! f(); // Не компилируется из-за неоднозначности
    }
}