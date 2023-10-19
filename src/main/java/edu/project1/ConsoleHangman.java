package edu.project1;

import java.util.Scanner;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

public class ConsoleHangman {

    private static final Logger LOGGER = Logger.getLogger(ConsoleHangman.class.getName());
    private static final Scanner IN = new Scanner(System.in);
    private static final String STOP = "?";
    private static final int MAX_FAILURES = 10;
    private final Session session;

    public ConsoleHangman(String... guessedWords) {
        this.session = new Session(new GuessedWords(guessedWords));
    }

    public void run() {
        while (session.isGameActive()) {
            LOGGER.info("Guess a letter:");
            String input = getUserInput();
            if (input.equals(STOP)) {
                break;
            }
            GuessResult guessAttempt = tryGuess(session, input);
            printState(guessAttempt);
        }
        tryGuess(session, STOP);
    }

    private String getUserInput() {
        int attempts = 0;
        while (attempts < MAX_FAILURES) {
            String input = IN.next();
            if (input.length() != 1) {
                LOGGER.info("Введите один символ для продолжения или \"?\" для выхода ");
                attempts++;
                continue;
            }
            return input;
        }
        LOGGER.info("Превышено количество попыток ввода");
        return STOP;
    }

    @NotNull
    private GuessResult tryGuess(Session session, String input) {
        GuessResult guessAttempt = session.guess(input.charAt(0));
        LOGGER.info(guessAttempt.message());
        return guessAttempt;
    }

    private void printState(GuessResult guess) {
        LOGGER.info(String.format("The word: %s\n\n", String.valueOf(guess.state())));
    }

}
