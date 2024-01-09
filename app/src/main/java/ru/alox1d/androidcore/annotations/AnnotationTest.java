package ru.alox1d.androidcore.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationTest {
    public static void main(String[] args) {
        Class iphoneClass = Iphone.class;
        SmartPhone smartPhone = (SmartPhone) iphoneClass.getAnnotation(SmartPhone.class);
        System.out.println("Annotation info from Iphone class: "
                + smartPhone.os()
                + " , "
                + smartPhone.yearOfCompanyCreation());
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface SmartPhone {
    String os() default "Android";

    int yearOfCompanyCreation();
}

@SmartPhone(os = "iOS", yearOfCompanyCreation = 1976)
class Iphone {
    String name;
    String price;
}
