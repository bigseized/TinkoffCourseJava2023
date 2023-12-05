package edu.project3.metrics.renderers;

import edu.project3.types.MetricsData;

public class MarkdownRenderer implements Renderer {

    public String render(MetricsData data) {
        StringBuilder outputString = new StringBuilder();
        int[] maxWidths = getMaximumWidth(data.getData());
        outputString.append("#### ").append(data.getTitle()).append("\n").append("\n");

        for (int j = 0; j < data.getData()[0].length; j++) {
            if (j == 1) {
                outputString.append(renderSplitRow(maxWidths, data));
            }
            outputString.append(renderRow(maxWidths, data, j));
        }
        return outputString.toString();
    }

    private int[] getMaximumWidth(String[]... data) {
        int[] maxWidths = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            int max = -1;
            for (int j = 0; j < data[i].length; j++) {
                max = Math.max(max, data[i][j].length());
            }
            maxWidths[i] = max + 2;
        }
        return maxWidths;
    }

    private String renderSplitRow(int[] maxWidths, MetricsData data) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("|");
        for (int g = 0; g < data.getData().length; g++) {
            outputString
                .append(String.format(":%" + (maxWidths[g] - 2) + "s:|", "")
                .replace(" ", "-"));
        }
        outputString.append("\n");
        return outputString.toString();
    }

    private String renderRow(int[] maxWidths, MetricsData data, int row) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("|");
        for (int i = 0; i < data.getData().length; i++) {
            outputString
                .append(String
                    .format(
                    "%" + (maxWidths[i] - data.getData()[i][row].length() - 1) + "s%s |", "",
                    data.getData()[i][row]
                ));
        }
        outputString.append("\n");
        return outputString.toString();
    }

}
