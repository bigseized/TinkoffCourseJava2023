package edu.project3.metrics.renderers;

import edu.project3.types.MetricsData;

public class AdocRenderer implements Renderer {
    public String render(MetricsData data) {
        StringBuilder outputString = new StringBuilder();
        int[] maxWidths = getMaximumWidth(data.getData());
        outputString.append("==== ").append(data.getTitle()).append("\n").append("\n");

        for (int row = 0; row < data.getData()[0].length; row++) {
            if (row == 0) {
                outputString.append(renderSplitRow(maxWidths));
            }
            if (row == 1) {
                outputString.append("\n");
            }
            outputString.append(renderRow(maxWidths, data, row));
            if (row == data.getData()[0].length - 1) {
                outputString.append(renderSplitRow(maxWidths));
            }
        }
        return outputString.toString();
    }

    private String renderSplitRow(int[] maxWidths) {
        StringBuilder outputString = new StringBuilder("|");

        for (int maxWidth : maxWidths) {
            outputString.append(String.format("%-" + maxWidth + "s=", "").replace(' ', '='));
        }
        outputString = new StringBuilder(outputString.substring(0, outputString.length() - 2));
        outputString.append("\n");
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

    private String renderRow(int[] maxWidths, MetricsData data, int row) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < data.getData().length; i++) {
            outputString
                .append(String
                    .format(
                        "|%" + (maxWidths[i] - data.getData()[i][row].length() - 1) + "s%s ", "",
                        data.getData()[i][row]
                    ));
        }
        outputString.append("\n");
        return outputString.toString();
    }
}
