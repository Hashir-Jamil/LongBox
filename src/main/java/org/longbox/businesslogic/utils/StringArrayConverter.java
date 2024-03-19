package org.longbox.businesslogic.utils;

public class StringArrayConverter {
    @Deprecated
    public static String[] stringToList(String stringToConvert) {
        return stringToConvert.split(",\\s*");
    }

    @Deprecated
    public static String listToString(String[] list) {
        String convertedString = "";
        if (list.length == 0) {
            return convertedString;
        }
        if (list.length == 1) {
            return list[0];
        }
        for (int i = 0; i < list.length - 1; i++) {
            convertedString = convertedString + list[i] + ", ";
        }
        convertedString = convertedString + list[list.length - 1];
        return convertedString;
    }
}
