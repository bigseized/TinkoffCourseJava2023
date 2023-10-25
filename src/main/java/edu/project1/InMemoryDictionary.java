package edu.project1;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InMemoryDictionary implements Dictionary {
    private final static Pattern WORD = Pattern.compile("^[a-zA-Z]+");
    private final static Random RANDOM = new Random();
    private final ArrayList<String> guessedWords;

    public InMemoryDictionary(String[] wordsToGuess) {
        this.guessedWords = findCorrectWords(wordsToGuess);
    }

    private ArrayList<String> findCorrectWords(String[] wordsToGuess) {
        ArrayList<String> correctWords = new ArrayList<>();
        for (String word : wordsToGuess) {
            Matcher matcher = WORD.matcher(word);
            if (matcher.matches()) {
                correctWords.add(word);
            }
        }
        return correctWords;
    }

    @Override
    public String randomWord() {
        int index = RANDOM.nextInt(0, guessedWords.size());
        return guessedWords.get(index);
    }
}

