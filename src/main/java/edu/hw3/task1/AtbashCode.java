package edu.hw3.task1;

public class AtbashCode {

    private final static int LOWEST_UPPERCASE_ASCII_INDEX = 65;
    private final static int HIGHEST_UPPERCASE_ASCII_INDEX = 90;
    private final static int LOWEST_LOWERCASE_ASCII_INDEX = 97;
    private final static int HIGHEST_LOWERCASE_ASCII_INDEX = 122;

    private AtbashCode() {
    }

    public static String encodeString(String word) {
        if (word.isEmpty()) {
            throw new IllegalArgumentException();
        }
        StringBuilder encodedString = new StringBuilder();
        for (char chr : word.toCharArray()) {
            encodedString.append(encodeChar(chr));
        }
        return encodedString.toString();
    }

    private static char encodeChar(char letter) {
        if (!isLatinAlphaInAscii(letter)) {
            return letter;
        }

        if (letter <= HIGHEST_UPPERCASE_ASCII_INDEX) {
            return encodeUppercaseChar(letter);
        }

        return encodeLowercaseChar(letter);
    }

    private static boolean isLatinAlphaInAscii(char letter) {
        return (letter >= LOWEST_UPPERCASE_ASCII_INDEX && letter <= HIGHEST_UPPERCASE_ASCII_INDEX)
            || (letter >= LOWEST_LOWERCASE_ASCII_INDEX && letter <= HIGHEST_LOWERCASE_ASCII_INDEX);
    }

    private static char encodeUppercaseChar(char letter) {
        return (char) (LOWEST_UPPERCASE_ASCII_INDEX - (letter - HIGHEST_UPPERCASE_ASCII_INDEX));
    }

    private static char encodeLowercaseChar(char letter) {
        return (char) (LOWEST_LOWERCASE_ASCII_INDEX - (letter - HIGHEST_LOWERCASE_ASCII_INDEX));
    }
}
