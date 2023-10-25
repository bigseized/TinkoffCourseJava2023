package edu.project1;

import java.util.Arrays;

public class Session {
    private final static int MAX_LENGTH_OF_WORD = 5;
    private final static String WIN = "WIN";
    private final static String LOSE = "LOSE";
    private final static char STOP_INPUT_SYMBOL = '?';
    private final static int MAX_ATTEMPTS = 5;
    private final String answer;
    private final char[] userAnswer;
    private int attempts;
    private boolean isGameActive = true;
    private String gameFinishedCause;


    public Session(Dictionary guessedWords) {
        this.answer = guessedWords.randomWord();
        if (answer.length() > MAX_LENGTH_OF_WORD) {
            throw new IllegalArgumentException("Загаданное слово не должно превышать 5 символов");
        }
        this.userAnswer = createUserAnswer(answer.length());
        this.attempts = 0;
    }

    private char[] createUserAnswer(int length) {
        char[] usrAnswer = new char[length];
        Arrays.fill(usrAnswer, '*');
        return usrAnswer;
    }

    GuessResult guess(char guess) {
        if (!isGameActive) {
            return gameStopCause();
        }
        if (guess == STOP_INPUT_SYMBOL) {
            return giveUp();
        }

        if (answer.indexOf(guess) != -1) { // буква угадана
            return handleHit(guess);
        } else { // буква не угадана
            return handleMiss();
        }
    }

    private GuessResult handleHit(char guess) {
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                userAnswer[i] = guess;
            }
        }
        if (answer.equals(String.valueOf(userAnswer))) {
            isGameActive = false;
            gameFinishedCause = WIN;
        }
        return new GuessResult.SuccessfulGuess(userAnswer, attempts, MAX_ATTEMPTS);
    }

    private GuessResult handleMiss() {
        attempts++;
        if (attempts == MAX_ATTEMPTS) {
            isGameActive = false;
            gameFinishedCause = LOSE;
        }
        return new GuessResult.FailedGuess(userAnswer, attempts, MAX_ATTEMPTS);
    }

    GuessResult giveUp() {
        isGameActive = false;
        gameFinishedCause = LOSE;
        return new GuessResult.Defeat(userAnswer, attempts, MAX_ATTEMPTS);
    }

    private GuessResult gameStopCause() {
        if (gameFinishedCause.equals(WIN)) {
            return new GuessResult.Win(userAnswer, attempts, MAX_ATTEMPTS);
        }
        return new GuessResult.Defeat(userAnswer, attempts, MAX_ATTEMPTS);
    }

    public boolean isGameActive() {
        return isGameActive;
    }
}
