package edu.hw1.task4;

public final class BrokenString {
    private BrokenString() {}

    public static String fixString(String brokenString) {
        String fixedString = "";

        for (int i = 1; i < brokenString.length(); i += 2) {
            fixedString = fixedString.concat(Character.toString(brokenString.charAt(i)));
            fixedString = fixedString.concat(Character.toString(brokenString.charAt(i - 1)));
        }

        if (fixedString.length() != brokenString.length()) {
            fixedString = fixedString.concat(Character.toString(brokenString.charAt(brokenString.length() - 1)));
        }

        return fixedString;
    }
}
