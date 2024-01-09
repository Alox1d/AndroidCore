package ru.alox1d.androidcore.lambdas;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PredicateTest {
    static void printStudents(ArrayList<Student> students, Predicate<Student> studentPredicate) {
        for (Student student : students) {
            if (studentPredicate.test(student)) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        Student student1 = new Student("Дмитрий", 'm', 31, 3, 9.0);
        Student student2 = new Student("Александра", 'f', 34, 4, 8.5);
        Student student3 = new Student("Алексей", 'm', 21, 2, 6.4);
        Student student4 = new Student("Елена", 'f', 19, 1, 9.6);
        Student student5 = new Student("Трофим", 'm', 41, 5, 7.2);
        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        System.out.println("------------------------");
        printStudents(students, student -> student.avgGrade > 8);
        System.out.println("------------------------");
        printStudents(students, student -> student.age > 30);
        System.out.println("------------------------");
        Predicate<Student> agePredicate = student -> student.age > 30;
        Predicate<Student> gradePredicate = student -> student.avgGrade < 9.0;
        Predicate<Student> sexPredicate = student -> student.sex == 'f';
        printStudents(students, agePredicate.and(gradePredicate).and(sexPredicate));
    }
}
