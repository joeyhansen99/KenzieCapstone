package com.kenzie.capstone.service;
import com.kenzie.capstone.service.dao.ExternalCardDao;
import com.kenzie.capstone.service.model.ExternalCard;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.List;

import javax.inject.Inject;

public class ExternalCardService {

    private ExternalCardDao cardDao;

    @Inject
    public ExternalCardService(ExternalCardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<ExternalCard> getCardSearchResults(String searchTerm) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(cardDao.sendSearchRequest(searchTerm), new TypeReference<>() { });
    }
}
