package ru.alox1d.androidcore.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardInfoFormatter {
    public static void main(String[] args) {
        String cardsInfo = "12345678912345670325987;"
                + "98765432165498750924654;"
                + "85274196345612381123333";
        //1234 5678 9123 4567 03/25 (987)
        Pattern pattern = Pattern.compile("(\\d{4})(\\d{4})(\\d{4})(\\d{4})(\\d{2})(\\d{2})(\\d{3})");
        Matcher matcher = pattern.matcher(cardsInfo);
        while (matcher.find()) {
            System.out.println(matcher.group(7));
        }
        String formattedInfo = matcher.replaceAll("$5/$6 $1 $2 $3 $4 ($7)");
        System.out.println(formattedInfo);
    }
}
