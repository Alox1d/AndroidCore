package ru.alox1d.androidcore.lambdas;

import java.util.function.Consumer;

public class ConsumerTest {
    public static void updateStudent(Student student, Consumer<Student> studentConsumer) {
        studentConsumer.accept(student);
    }

    public static void main(String[] args) {
        Student student1 = new Student("Дмитрий", 'm', 31, 3, 9.0);
        Student student2 = new Student("Александра", 'f', 34, 4, 8.5);

        updateStudent(student1, student -> student.name += " Золотых");
        System.out.println(student1);

        updateStudent(student2, student -> student.course += 1);
        System.out.println(student2);
    }
}