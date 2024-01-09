package ru.alox1d.androidcore.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class employeeClass = Class.forName("reflection.Employee");
        Class employeeClass2 = Employee.class;
        Employee emp = new Employee();
        Class employeeClass3 = emp.getClass();

        Field field = employeeClass.getField("id");
        System.out.println("Field id type is " + field.getType());
        System.out.println("*** ***");

        Field[] fields = employeeClass.getFields();
        for (Field f : fields) {
            System.out.println("Field " + f.getName() + " type is " + f.getType());
        }
        System.out.println("*** ***");

        Field[] allFields = employeeClass.getDeclaredFields();
        for (Field f : allFields) {
            System.out.println("Field " + f.getName() + " type is " + f.getType());
        }
        System.out.println("*** ***");

        Method method = employeeClass.getMethod("setSalary", int.class);
        System.out.println("Return type of method " + method.getName() + " is " + method.getReturnType() +
                ", parameter types: " + Arrays.toString(method.getParameterTypes()));
        System.out.println("*** ***");

        Method[] methods = employeeClass.getDeclaredMethods();
        for (Method m : methods) {
            if (Modifier.isPublic(m.getModifiers())) {
                System.out.println("Public method " + m.getName()
                        + " has return type " + m.getReturnType()
                        + " and parameter types: " + Arrays.toString(m.getParameterTypes()));
            }
        }
        System.out.println("*** ***");

        Constructor[] constructors = employeeClass.getConstructors();
        for (Constructor c : constructors) {
            System.out.println("Constructor " + c.getName()
                    + " has " + c.getParameterCount()
                    + " params, their types : " + Arrays.toString(c.getParameterTypes()));
        }
    }
}
