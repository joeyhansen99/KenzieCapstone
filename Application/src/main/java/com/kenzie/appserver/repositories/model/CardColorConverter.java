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
        }
        return result;
    }

    @Override
    public List<CardColor> unconvert(List<String> object) {
        List<CardColor> result = new ArrayList<>();
        if (object != null) {
            for (String s : object) {
                if (s.equals(CardColor.B.toString())) {
                    result.add(CardColor.B);
                }
                if (s.equals(CardColor.U.toString())) {
                    result.add(CardColor.U);
                }
                if (s.equals(CardColor.G.toString())) {
                    result.add(CardColor.G);
                }
                if (s.equals(CardColor.R.toString())) {
                    result.add(CardColor.R);
                }
                if (s.equals(CardColor.W.toString())) {
                    result.add(CardColor.W);
                }
                if (s.equals(CardColor.COLORLESS.toString())) {
                    result.add(CardColor.COLORLESS);
                }
            }
        }
        return result;
    }

}
