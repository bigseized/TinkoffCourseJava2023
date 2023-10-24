package edu.hw3.task2;

import java.util.ArrayList;

public class BracketClusterize {

    private BracketClusterize() {}

    public static ArrayList<String> clusterize(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Строка не может быть пустой");
        }
        int openBracketCounter = 0;
        int closeBracketCounter = 0;
        StringBuilder cluster = new StringBuilder();
        ArrayList<String> clustersArray = new ArrayList<>();

        for (char bracket: input.toCharArray()) {
            if (bracket != '(' && bracket != ')') {
                throw new IllegalArgumentException("Присутствуют посторонние символы");
            }
            if (bracket == '(') {
                openBracketCounter++;
                cluster.append(bracket);
            }
            if (bracket == ')') {
                closeBracketCounter++;
                cluster.append(bracket);
            }
            if (openBracketCounter == closeBracketCounter) {
                clustersArray.add(cluster.toString());
                cluster = new StringBuilder();
            }
            if (!cluster.isEmpty() && cluster.charAt(0) == ')') {
                throw new IllegalArgumentException("Кластер не может начинаться с закрывающей скобки");
            }
        }
        if (!cluster.isEmpty()) {
            throw new IllegalArgumentException("Невозможно полностью кластеризовать эту строку");
        }
        return clustersArray;
    }
}
