package ru.alox1d.androidcore.nested_classes;

public class AnonInnerClass {

    public static void main(String[] args) {
        MathJava math = new MathJava() {
            @Override
            public int doOperation() {
                return 1 + 1;
            }
        };
        // Same
        // MathJava math = () -> 1+1;

        System.out.println(math.doOperation());
    }
}

interface MathJava {
    int doOperation();
}