package edu.project3.dataPreparation.dataReceivers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.apache.commons.validator.routines.UrlValidator;

public class HttpReceiver extends AbstractReceiver {

    @SneakyThrows public Stream<String> loadLogs(String path) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<Stream<String>> response = httpClient.send(
                HttpRequest.newBuilder()
                    .uri(URI.create(path))
                    .GET()
                    .build(), HttpResponse.BodyHandlers.ofLines());
            return response.body().toList().stream();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Stream<String>> get(String path) {
        UrlValidator urlValidator = new UrlValidator(new String[] {"http", "https"});
        if (urlValidator.isValid(path)) {
            super.paths = List.of(path);
            return Optional.of(loadLogs(path));
        }
        return getNext(path);
    }
}
