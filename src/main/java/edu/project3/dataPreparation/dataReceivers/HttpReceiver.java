package edu.project3.dataPreparation.dataReceivers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.UnresolvedAddressException;
import java.util.List;
import java.util.stream.Stream;

public class HttpReceiver {

    private HttpReceiver() {
    }

    public static List<String> loadLogs(String path) throws UnresolvedAddressException {

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<Stream<String>> response = httpClient.send(
                HttpRequest.newBuilder()
                    .uri(URI.create(path))
                    .GET()
                    .build(), HttpResponse.BodyHandlers.ofLines());
            return response.body().toList();
        } catch (UnresolvedAddressException e) {
            throw new IllegalArgumentException("Неверный URL");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
