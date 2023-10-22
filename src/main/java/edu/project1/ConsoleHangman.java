package edu.project1;

import java.util.Scanner;
import java.util.logging.Logger;

public class ConsoleHangman {

    private static final Logger LOGGER = Logger.getLogger(ConsoleHangman.class.getName());
    private static final String STOP_INPUT_SYMBOL = "?";
    private static final int MAX_FAILURES = 10;
    private final Session session;

    public ConsoleHangman(String... wordsToGuess) {
        this.session = new Session(new InMemoryDictionary(wordsToGuess));
    }

    public void run() {
        while (session.isGameActive()) {
            LOGGER.info("Guess a letter:");
            String input = getUserInput();
            if (input.equals(STOP_INPUT_SYMBOL)) {
                LOGGER.info("Игра остановлена вводом специального символа\n ");
                break;
            }
            GuessResult guessAttempt = tryGuess(session, input);
            printState(guessAttempt);
        }
        stopProgram(session);
    }

    private String getUserInput() {
        Scanner in = new Scanner(System.in);
        int attempts = 0;
        while (attempts < MAX_FAILURES) {
            String input = in.next();
            if (input.length() != 1) {
                LOGGER.info("Введите один символ для продолжения или \"?\" для выхода ");
                attempts++;
                continue;
            }
            return input.toLowerCase();
        }
        LOGGER.info("Превышено количество попыток ввода");
        return STOP_INPUT_SYMBOL;
    }

    private GuessResult tryGuess(Session session, String input) {
        GuessResult guessAttempt = session.guess(input.charAt(0));
        LOGGER.info(guessAttempt.message());
        return guessAttempt;
    }

    private void stopProgram(Session session) {
        GuessResult guessAttempt = session.guess(STOP_INPUT_SYMBOL.charAt(0));
        LOGGER.info(guessAttempt.message());
    }

    private void printState(GuessResult guess) {
        LOGGER.info(String.format("The word: %s\n\n", String.valueOf(guess.state())));
    }

}
