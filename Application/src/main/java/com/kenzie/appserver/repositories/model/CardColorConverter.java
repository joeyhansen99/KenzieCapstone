package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.kenzie.appserver.service.model.CardColor;

import java.util.ArrayList;
import java.util.List;

public class CardColorConverter implements DynamoDBTypeConverter<List<String>, List<CardColor>> {

    public CardColorConverter() {
    }

    @Override
    public List<String> convert(List<CardColor> object) {
        List<String> result = new ArrayList<>();
        if (object != null) {
            for (CardColor c : object) {
                result.add(c.toString());
            }
            object.forEach(e -> result.add(e.name()));
        }
        return result;
    }

    @Override
    public List<CardColor> unconvert(List<String> object) {
        List<CardColor> result = new ArrayList<>();
        if (object != null) {
            object.forEach(e -> result.add(CardColor.valueOf(e)));
        }
        return result;
    }
}
