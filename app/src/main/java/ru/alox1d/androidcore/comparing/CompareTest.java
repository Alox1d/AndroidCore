package ru.alox1d.androidcore.comparing;

import java.util.ArrayList;
import java.util.Collections;

public class CompareTest {
    public static void main(String[] args) {
        Employee e1 = new Employee(100, "Дмитрий", "Золотых", 123);
        Employee e2 = new Employee(50, "Александр", "Федоров", 321);
        Employee e3 = new Employee(200, "Александр", "Сидоров", 231);

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

        System.out.println("Лист до сортировки: ");
        System.out.println(employees);
        Collections.sort(employees);
        System.out.println("Лист после сортировки по ID: ");
        System.out.println(employees);
        Collections.sort(employees, new NameComparator());
        System.out.println("Лист после сортировки по имени и фамилии: ");
        System.out.println(employees);
        Collections.sort(employees, (emp1, emp2) -> emp1.salary - emp2.salary);
        System.out.println("Лист после сортировки по зарплате: ");
        System.out.println(employees);
    }
}
