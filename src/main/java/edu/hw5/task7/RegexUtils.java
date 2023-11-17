package edu.hw5.task7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    private static final Pattern FIRST_PATTERN = Pattern.compile("^[01]{2}0[01]*$");
    private static final Pattern SECOND_PATTERN = Pattern.compile("^(0|1)[01]*\\1$");
    private static final Pattern THIRD_PATTERN = Pattern.compile("^[01]{1,3}$");

    private RegexUtils() {}

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

}
