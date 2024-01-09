package ru.alox1d.androidcore.inheritance;


class A {
    String s1 = "A_s1";

    static class B extends A {
        // Attention! Shadows s1 from A
        String s1 = "B_s1";

        B() {
            System.out.println(s1);
        }

    }

    public static void main(String[] args) {
        B b = new B();
    }
}
