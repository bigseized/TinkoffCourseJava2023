package edu.hw1.task4;

public final class BrokenString {
    private BrokenString() {}

    public static String fixString(String brokenString) {
        if (brokenString.isEmpty()) {
            throw new IllegalArgumentException();
        }

        StringBuilder fixedString = new StringBuilder();

        for (int i = 1; i < brokenString.length(); i += 2) {
            fixedString.append(brokenString.charAt(i));
            fixedString.append(brokenString.charAt(i - 1));
        }

        if (fixedString.length() != brokenString.length()) {
            fixedString.append(brokenString.charAt(brokenString.length() - 1));
        }

        return fixedString.toString();
    }
}
