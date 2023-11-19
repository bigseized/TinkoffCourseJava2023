package edu.project3.dataPreparation.dataParsers;

import edu.project3.types.RawArgs;

public class CLIArgsParser {

    private final static String PATH_FLAG = "--path";
    private final static String FROM_FLAG = "--from";
    private final static String TO_FLAG = "--to";
    private final static String FORMAT_FLAG = "--format";
    private final static String EMPTY = "";

    public RawArgs parseArguments(String[] args) {
        String activeFlag = EMPTY;
        var rawArgs = new RawArgs();

        for (var entry : args) {
            if (!activeFlag.isBlank()) {
                switch (activeFlag) {
                    case PATH_FLAG -> rawArgs.setDataSource(entry);
                    case FROM_FLAG -> rawArgs.setStartDate(entry);
                    case TO_FLAG -> rawArgs.setEndDate(entry);
                    case FORMAT_FLAG -> rawArgs.setFormat(entry);
                    default -> throw new IllegalArgumentException("Неверный формат ввода");
                }
                activeFlag = EMPTY;
                continue;
            }
            activeFlag = entry;
        }
        return setBaseParams(rawArgs);
    }

    private RawArgs setBaseParams(RawArgs rawArgs) {
        if (rawArgs.getFormat() == null) {
            rawArgs.setFormat("markdown");
        }
        if (rawArgs.getStartDate() == null) {
            rawArgs.setStartDate("-");
        }
        if (rawArgs.getEndDate() == null) {
            rawArgs.setEndDate("-");
        }
        if (rawArgs.getDataSource() == null) {
            throw new IllegalArgumentException("--path является обязательным параметром");
        }
        return rawArgs;
    }

}
