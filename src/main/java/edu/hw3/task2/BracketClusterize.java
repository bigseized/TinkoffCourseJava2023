package edu.hw3.task2;

import java.util.ArrayList;

public class BracketClusterize {
    private final static char OPEN_BRACKET = '(';
    private final static char CLOSE_BRACKET = ')';

    private BracketClusterize() {
    }

    public static ArrayList<String> clusterize(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Строка не может быть пустой");
        }
        int openBracketCounter = 0;
        int closeBracketCounter = 0;
        StringBuilder cluster = new StringBuilder();
        ArrayList<String> clustersArray = new ArrayList<>();

        for (char bracket : input.toCharArray()) {
            if (bracket != OPEN_BRACKET && bracket != CLOSE_BRACKET) {
                throw new IllegalArgumentException("Присутствуют посторонние символы");
            }
            if (bracket == OPEN_BRACKET) {
                openBracketCounter++;
                cluster.append(bracket);
            }
            if (bracket == CLOSE_BRACKET) {
                closeBracketCounter++;
                cluster.append(bracket);
            }
            if (openBracketCounter == closeBracketCounter) {
                clustersArray.add(cluster.toString());
                cluster = new StringBuilder();
            }
            if (!cluster.isEmpty() && cluster.charAt(0) == CLOSE_BRACKET) {
                throw new IllegalArgumentException("Кластер не может начинаться с закрывающей скобки");
            }
        }
        if (!cluster.isEmpty()) {
            throw new IllegalArgumentException("Невозможно полностью кластеризовать эту строку");
        }
        return clustersArray;
    }
}
