package me.fani.michael.util;

public class PasswordUtil {

    public static boolean isGoodPassword(String passwd) {
        return passwd.length() >= 8;
    }

}
