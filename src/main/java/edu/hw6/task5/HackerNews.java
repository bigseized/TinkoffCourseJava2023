package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private HackerNews() {
    }

    private final static Pattern TITLE_FINDER = Pattern.compile("\"title\":\"([^\"]*)\"");

    public static long[] hackerNewsTopStories() {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<String> response = httpClient.send(
                HttpRequest.newBuilder()
                    .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                    .GET()
                    .build(), HttpResponse.BodyHandlers.ofString());
            return parseStringResponse(response.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String news(long id) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<String> response = httpClient.send(
                HttpRequest.newBuilder()
                    .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
                    .GET()
                    .build(), HttpResponse.BodyHandlers.ofString());

            Matcher matcher = TITLE_FINDER.matcher(response.body());
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return "";
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static long[] parseStringResponse(String response) {
        String[] strings = response
            .replaceAll("\\[", "")
            .replaceAll("]", "")
            .split(",");
        long[] ids = new long[strings.length];

        for (int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(strings[i]);
        }
        return ids;
    }
}
