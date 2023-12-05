package edu.project3.dataPreparation.dataReceivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class AbstractReceiver {
    protected AbstractReceiver nextReceiver;
    protected List<String> paths = new ArrayList<>();

    public static AbstractReceiver makeChain(AbstractReceiver first, AbstractReceiver... dateParsers) {
        AbstractReceiver currentParser = first;
        for (var parser : dateParsers) {
            currentParser.nextReceiver = parser;
            currentParser = parser;
        }
        return first;
    }

    public abstract Optional<Stream<String>> get(String path);

    protected Optional<Stream<String>> getNext(String path) {
        if (nextReceiver != null) {
            return nextReceiver.get(path);
        } else {
            return Optional.empty();
        }
    }

    public List<String> getUsedPaths() {
        return paths;
    }
}
