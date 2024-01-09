package ru.alox1d.androidcore.string;

public class SimpleString {
    public static void main(String[] args) {
        int primitive = 1;
        foo(Integer.toString(primitive));
        foo(String.valueOf(primitive));

        Integer box = 2;
        foo(box.toString());
    }

    static void foo(String s) {
        System.out.println(s);
    }
}
