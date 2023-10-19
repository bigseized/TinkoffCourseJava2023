package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class GuessedWords implements Dictionary {
    private final String[] guessedWords;
    private final static Random RANDOM = new Random();

    public GuessedWords(String[] guessedWords) {
        this.guessedWords = guessedWords;
    }

    @Override
    @NotNull
    public String randomWord() {
        int index = RANDOM.nextInt(0, guessedWords.length);
        return guessedWords[index];
    }
}
