package ru.alox1d.androidcore.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException
            , InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class employeeClass = Class.forName("reflection.Employee");

        Constructor<Employee> noArgsConstructor = employeeClass.getConstructor();
        Employee employeeObject1 = noArgsConstructor.newInstance();
        System.out.println(employeeObject1);
        System.out.println("*** ***");

        Constructor threeArgsConstructor = employeeClass.getConstructor(int.class, String.class, String.class);
        Object employeeObject2 = threeArgsConstructor.newInstance(5, "Дмитрий", "IT");
        System.out.println(employeeObject2);
        System.out.println("*** ***");

        Method setSalaryMethod = employeeClass.getMethod("setSalary", int.class);
        setSalaryMethod.invoke(employeeObject2, 500);
        System.out.println(employeeObject2);
        System.out.println("*** ***");

        Field salaryField = employeeClass.getDeclaredField("salary");
        salaryField.setAccessible(true);
        salaryField.set(employeeObject2, 1000);
        System.out.println(employeeObject2);
    }
}
