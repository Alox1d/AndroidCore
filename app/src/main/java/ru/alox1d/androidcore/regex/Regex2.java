package ru.alox1d.androidcore.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex2 {
    public static void main(String[] args) {
        String s1 = "ABCD ABCE ABCFABCGABCH";
        Pattern pattern1 = Pattern.compile("ABC[D-F]");
        Matcher matcher = pattern1.matcher(s1);

        while (matcher.find()) {
            System.out.println("Position: " + matcher.start() + "   " + matcher.group());
        }
    }
}
