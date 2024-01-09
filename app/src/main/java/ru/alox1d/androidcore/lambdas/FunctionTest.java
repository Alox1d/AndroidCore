package ru.alox1d.androidcore.lambdas;

import java.util.List;
import java.util.function.Function;

public class FunctionTest {

    public static double averageSomething(List<Student> students, Function<Student, Double> function) {
        double result = 0.0;
        for (Student s : students) {
            result += function.apply(s);
        }
        return result / students.size();
    }

    public static void main(String[] args) {
        Student student1 = new Student("Дмитрий", 'm', 31, 3, 9.0);
        Student student2 = new Student("Александра", 'f', 34, 4, 8.5);
        Student student3 = new Student("Алексей", 'm', 21, 2, 6.4);
        Student student4 = new Student("Елена", 'f', 19, 1, 9.6);
        Student student5 = new Student("Трофим", 'm', 41, 5, 7.2);
        List<Student> students = List.of(student1, student2, student3, student4, student5);

        System.out.print("Средняя оценка всех студентов: ");
        System.out.println(averageSomething(students, student -> student.avgGrade));

        System.out.print("Средний курс всех студентов: ");
        System.out.println(averageSomething(students, student -> (double) student.course));
    }
}
