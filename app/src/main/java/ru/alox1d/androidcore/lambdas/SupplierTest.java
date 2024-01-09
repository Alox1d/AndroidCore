package ru.alox1d.androidcore.lambdas;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SupplierTest {
    public static ArrayList<Student> createTreeStudents(Supplier<Student> studentSupplier) {
        ArrayList<Student> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            result.add(studentSupplier.get());
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Student> treeStudents =
                createTreeStudents(() -> new Student("Александр", 'm', 34, 3, 7.7));
        System.out.println("Вот лист с тремя Шуриками: " + treeStudents);
    }
}
