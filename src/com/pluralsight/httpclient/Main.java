package com.pluralsight.httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws Exception {
        // HttpClientExample();

        HttpClientAsyncExample();
    }

    private static void HttpClientExample() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder(URI.create("https://pluralsight.com"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(req, BodyHandlers.ofString());

        System.out.println(response.headers().map());
    }

    private static void HttpClientAsyncExample() {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest req = HttpRequest.newBuilder(URI.create("https://google.com"))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> resFuture = httpClient.sendAsync(req, BodyHandlers.ofString());

        resFuture.thenAccept(res -> System.out.println(res.version()));
        resFuture.join();
    }
}
