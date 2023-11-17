package edu.hw5.task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheck {

    private PasswordCheck() {
    }

    private final static Pattern SPECIAL_SYMBOL_PATTERN = Pattern.compile("[~!@#$%^&*|]");

    public static boolean isPasswordCorrect(String password) {
        Matcher matcher = SPECIAL_SYMBOL_PATTERN.matcher(password);
        return matcher.find();
    }
}
