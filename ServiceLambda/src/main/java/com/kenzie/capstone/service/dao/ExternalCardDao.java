package com.kenzie.capstone.service.dao;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExternalCardDao {

    private HttpClient httpClient;
    private static final String EXTERNAL_API_ADDRESS = "https://api.magicthegathering.io/v1/cards";

    @Inject
    public ExternalCardDao(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String sendSearchRequest(String searchTerm) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("name",searchTerm)
                .uri(URI.create(EXTERNAL_API_ADDRESS))
                .build();

        return httpClient.send(request,HttpResponse.BodyHandlers.ofString()).body();
    }
}
