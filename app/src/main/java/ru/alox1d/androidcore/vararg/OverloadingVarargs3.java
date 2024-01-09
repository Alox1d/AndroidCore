package ru.alox1d.androidcore.vararg;

public class OverloadingVarargs3 {
    //: initialization/0verloadingVarargs2.java // {CompileTimeError} (не компилируется)
    static void f(float i, Character... args) {
        System.out.println("first");
    }

    static void f(char c, Character... args) {
        System.out.print("second");
    }

    public static void main(String[] args) {
        f(1);
//        f(1,2); ERROR
        f(1, 'a');
        f('a', 'b');
    }
}
