package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubStringsFinder {

    private SubStringsFinder() {
    }

    public static boolean isSubString(String stringToCheck, String subString) {
        Pattern subStringPattern = Pattern.compile(subString);
        Matcher matcher = subStringPattern.matcher(stringToCheck);
        return matcher.find();
    }

}
