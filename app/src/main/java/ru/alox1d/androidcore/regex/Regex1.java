package ru.alox1d.androidcore.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex1 {
    public static void main(String[] args) {
        String s = "Иванов Василий, Россия, Москва, Улица Ленина, 51, квартира 48" +
                "email: vivanov@mail.ru, индекс: АА99, телефон: +123456789;" +
                "Петрова Мария, Беларусь, Минск, Улица Ломоносова, 33, квартира 18" +
                "email: masha@yandex.ru, индекс: BEL54, телефон: +987654321;" +
                "Джеки Чан, КНР, Пекин, Улица Красного дракона, 87, квартира 21" +
                "email: superjeckie@gmail.com, индекс: CHI23, телефон: +136478952.";

        Pattern pattern1 = Pattern.compile("\\w+"); // все слова
        Pattern pattern2 = Pattern.compile("\\b\\d{2}\\b"); //номера квартир и домов
        Pattern pattern3 = Pattern.compile("\\+\\d{9}"); //номера телефонов
        Pattern pattern4 = Pattern.compile("\\w+@\\w+\\.(com|ru)"); //email

        Matcher matcher = pattern4.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
