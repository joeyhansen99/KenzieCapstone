package com.kenzie.capstone.service.client;
import com.kenzie.capstone.service.model.Card;
import com.kenzie.capstone.service.model.ExternalCard;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


public class LambdaServiceClient {

    private static final String GET_CARD_ENDPOINT = "cards/{cardId}";

    private final ObjectMapper mapper;

    public LambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public List<ExternalCard> getCardData(String name) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_CARD_ENDPOINT.replace("{cardId}", name));
        List<ExternalCard> list;
        try {
            list = mapper.readValue(response, new TypeReference<>() { });
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return list;
    }

    // code Jet Added
    public List<Card> getAllCardsData() {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_CARD_ENDPOINT.replace("{cardId}", "all"));
        List<Card> cardDataList;
        try {
            cardDataList = mapper.readValue(response, new TypeReference<>() { });
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return cardDataList;
    }

}
