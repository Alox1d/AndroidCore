package ru.alox1d.androidcore.regex;

import java.util.Arrays;

public class Regex3 {
    public static void main(String[] args) {
        String s = "superjeckie@gmail.com";
        boolean result = s.matches("\\w+@\\w+\\.(com|ru)");
        System.out.println(result);

        String[] substrings = s.split("@");
        System.out.println(Arrays.toString(substrings));
    }
}
