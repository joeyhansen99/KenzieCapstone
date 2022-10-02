package com.kenzie.capstone.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.ExternalCard;
import com.kenzie.capstone.service.dao.ExternalCardDao;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;


public class ExternalCardService {

    private ExternalCardDao cardDao;

    @Inject
    public ExternalCardService(ExternalCardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<ExternalCard> getCardSearchResults(String searchTerm) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(cardDao.sendSearchRequest(searchTerm), new TypeReference<>() {});
    }
}
