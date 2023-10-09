package edu.hw1.task5;


public final class Palindrome {
    final static int DEC = 10;
    final static int MAX_VALUE_OF_ONE_DIGIT_NUMBER = 9;

    private Palindrome() {}

    private static boolean isPalindrome(int number) {
        int numberForOperations = number;
        int reverseNumber = 0;

        while (numberForOperations > 0) {
            reverseNumber = reverseNumber * DEC + numberForOperations % DEC;
            numberForOperations /= DEC;
        }

        return (reverseNumber == number);
    }

    private static int createNewNumber(int number) {
        String numberInStringFormat = String.valueOf(number);
        String newNumberInStringFormat = "";

        for (int i = 0; i < numberInStringFormat.length(); i += 2) {
            int firstDigit = Integer.parseInt(String.valueOf(numberInStringFormat.charAt(i)));
            if (i == numberInStringFormat.length() - 1) {
                newNumberInStringFormat = newNumberInStringFormat.concat(String.valueOf(firstDigit));
                break;
            }
            int secondDigit = Integer.parseInt(String.valueOf(numberInStringFormat.charAt(i + 1)));
            newNumberInStringFormat = newNumberInStringFormat.concat(String.valueOf(firstDigit + secondDigit));
        }
        return Integer.parseInt(newNumberInStringFormat);
    }

    public static boolean isPalindromeDescendant(int number) {
        int newNumber = number;
        while (newNumber > MAX_VALUE_OF_ONE_DIGIT_NUMBER) {
            if (isPalindrome(newNumber)) {
                return true;
            }
            newNumber = createNewNumber(newNumber);
        }
        return false;
    }
}
