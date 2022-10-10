package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.kenzie.appserver.service.model.CardType;

import java.util.ArrayList;
import java.util.List;

public class CardTypeConverter implements DynamoDBTypeConverter<List<String>, List<CardType>> {

    public CardTypeConverter() {
    }

    @Override
    public List<String> convert(List<CardType> object) {
        List<String> result = new ArrayList<>();
        if (object != null) {
            object.forEach(e -> result.add(e.name()));
        }
        return result;
    }

    @Override
    public List<CardType> unconvert(List<String> object) {
        List<CardType> result = new ArrayList<>();
        if (object != null) {
            object.forEach(e -> result.add(CardType.valueOf(e)));
        }
        return result;
    }
}
