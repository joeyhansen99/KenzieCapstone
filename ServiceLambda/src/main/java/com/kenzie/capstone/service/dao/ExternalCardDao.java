package com.kenzie.capstone.service.dao;

import java.io.IOException;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.inject.Inject;

public class ExternalCardDao {

    private static final String EXTERNAL_API_ADDRESS = "https://api.magicthegathering.io/v1/cards";
    private HttpClient httpClient;

    @Inject
    public ExternalCardDao(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String sendSearchRequest(String searchTerm) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(EXTERNAL_API_ADDRESS + "?name=" + searchTerm))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}
