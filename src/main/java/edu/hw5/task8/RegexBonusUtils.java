package edu.hw5.task8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexBonusUtils {
    private static final Pattern FIRST_PATTERN = Pattern.compile("^[01]([01]{2})*$");
    private static final Pattern SECOND_PATTERN = Pattern.compile("^(0([01]{2})*)|(1[01]([01]{2})*)$");
    private static final Pattern THIRD_PATTERN = Pattern.compile("^(1*01*01*01*)+$|^1*$");
    private static final Pattern FOURTH_PATTERN = Pattern.compile("^(?!11$|111$)[01]*");
    private static final Pattern FIFTH_PATTERN = Pattern.compile("^(1[01])*1?$");
    private static final Pattern SIXTH_PATTERN = Pattern.compile("^(0+1?0+)$|^(0{2,}1?)$|^(1?0{2,})$");
    private static final Pattern SEVENTH_PATTERN = Pattern.compile("^((?!11)[01]?(?!11))+$");

    private RegexBonusUtils() {
    }

    public static boolean isMatchesFirst(String s) {
        Matcher matcher = FIRST_PATTERN.matcher(s);
        return matcher.matches();
    }

    public static boolean isMatchesSecond(String s) {
        Matcher matcher = SECOND_PATTERN.matcher(s);
        return matcher.matches();
    }

    public static boolean isMatchesThird(String s) {
        Matcher matcher = THIRD_PATTERN.matcher(s);
        return matcher.matches();
    }

    public static boolean isMatchesFourth(String s) {
        Matcher matcher = FOURTH_PATTERN.matcher(s);
        return matcher.matches();
    }

    public static boolean isMatchesFifth(String s) {
        Matcher matcher = FIFTH_PATTERN.matcher(s);
        return matcher.matches();
    }

    public static boolean isMatchesSixth(String s) {
        Matcher matcher = SIXTH_PATTERN.matcher(s);
        return matcher.matches();
    }

    public static boolean isMatchesSeventh(String s) {
        Matcher matcher = SEVENTH_PATTERN.matcher(s);
        return matcher.matches();
    }
}
