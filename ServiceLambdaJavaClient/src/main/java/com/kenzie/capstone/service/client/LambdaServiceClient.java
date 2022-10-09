package com.kenzie.capstone.service.client;
import com.kenzie.capstone.service.model.Card;
import com.kenzie.capstone.service.model.ExampleData;
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
            list = mapper.readValue(response, new TypeReference<>(){});
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
            cardDataList = mapper.readValue(response, new TypeReference<>(){});
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return cardDataList;
    }

    // code added from original file - BEGINNING
    private static final String GET_EXAMPLE_ENDPOINT = "example/{id}";
    private static final String SET_EXAMPLE_ENDPOINT = "example";

    public ExampleData getExampleData(String id) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_EXAMPLE_ENDPOINT.replace("{id}", id));
        ExampleData exampleData;
        try {
            exampleData = mapper.readValue(response, ExampleData.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return exampleData;
    }

    public ExampleData setExampleData(String data) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.postEndpoint(SET_EXAMPLE_ENDPOINT, data);
        ExampleData exampleData;
        try {
            exampleData = mapper.readValue(response, ExampleData.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return exampleData;
    }
    // code added from original file - END
}
