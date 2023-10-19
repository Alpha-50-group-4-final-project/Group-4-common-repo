package com.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelpers {

    public static boolean validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            System.out.println(message);
            return false;
        }
        return true;
    }

    public static boolean validateDecimalRange(double value, double min, double max, String message) {
        if (value < min || value > max) {
            System.out.println(message);
            return false;
        }
        return true;
    }

    public static boolean validatePattern(String value, String pattern) {
        Pattern patternToMatch = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternToMatch.matcher(value);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    public static boolean validateString(String value, int minLenght, int maxLenght, String errorMessage) {
        if (!validateIntRange(value.length(), minLenght, maxLenght, errorMessage)) {
            return false;
        }
        return true;
    }

    public static boolean userName(String value, int minLenght, int maxLenght, String errorMessage) {
        if (!validateString(value, minLenght, maxLenght, errorMessage) || !validatePattern(value, "^[a-zA-Z]+$")) {
            return false;
        }
        return true;
    }

}




