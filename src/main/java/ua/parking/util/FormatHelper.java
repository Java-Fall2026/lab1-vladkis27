package ua.parking.util;

public class FormatHelper {
    static String normalize(String str) {
        if(str == null) return null;
        return str.trim();
    }

    static String capitalize(String str) {
        if(str == null) return null;
        return str.trim().toUpperCase();
    }
}