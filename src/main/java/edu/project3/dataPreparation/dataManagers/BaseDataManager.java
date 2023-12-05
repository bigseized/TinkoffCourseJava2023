package edu.project3.dataPreparation.dataManagers;

import edu.project3.dataPreparation.dataParsers.CLIArgsParser;
import edu.project3.types.ParsedData;
import edu.project3.types.RawArgs;
import java.util.List;

public class BaseDataManager {

    private final ArgsResolver argsResolver = new ArgsResolver();
    private RawArgs rawArgs = new RawArgs();

    public ParsedData getData(String[] commandLineArguments) {
        rawArgs = new CLIArgsParser().parseArguments(commandLineArguments);
        return argsResolver.getParsedData(rawArgs);
    }

    public List<String> getPaths() {
        return argsResolver.getPaths();
    }

    public RawArgs getRawargs() {
        return rawArgs;
    }
}
