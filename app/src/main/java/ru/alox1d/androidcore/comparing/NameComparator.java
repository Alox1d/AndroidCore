package ru.alox1d.androidcore.comparing;

import java.util.Comparator;

public class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        int result = emp1.name.compareTo(emp2.name);
        if (result == 0) {
            result = emp1.surname.compareTo(emp2.surname);
        }
        return result;
    }
}
