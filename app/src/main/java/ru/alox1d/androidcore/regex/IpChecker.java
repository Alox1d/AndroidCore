package ru.alox1d.androidcore.regex;

import java.util.regex.Pattern;

public class IpChecker {
    static void checkIp(String ip) {
        String pattern = "((25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)\\.){3}(25[0-5]|2\\d[0-4]|[0-1]?\\d?\\d)";
        //                 250-255 | 200-249 | 0-199          . x3  250-255|200-249  | 0-199
        System.out.println(ip + " is OK? " + Pattern.matches(pattern, ip));
    }

    public static void main(String[] args) {
        String ip1 = "255.106.256.01";
        String ip2 = "192.168.205.90";
        checkIp(ip1);
        checkIp(ip2);
    }
}