package com.kenzie.appserver.repositories.model;
import com.kenzie.appserver.service.model.CardType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.ArrayList;
import java.util.List;

public class CardTypeConverter implements DynamoDBTypeConverter<List<String>, List<CardType>> {

    public CardTypeConverter() {
    }

    @Override
    public List<String> convert(List<CardType> object) {
        List<String> result = new ArrayList<>();
        if (object != null) {
            for (CardType t : object) {
                result.add(t.toString());
            }
        }
        return result;
    }

    @Override
    public List<CardType> unconvert(List<String> object) {
        List<CardType> result = new ArrayList<>();
        if (object != null) {
            for (String s : object) {
                if (s.equals(CardType.ARTIFACT.toString())) {
                    result.add(CardType.ARTIFACT);
                }
                if (s.equals(CardType.CONSPIRACY.toString())) {
                    result.add(CardType.CONSPIRACY);
                }
                if (s.equals(CardType.CREATURE.toString())) {
                    result.add(CardType.CREATURE);
                }
                if (s.equals(CardType.ENCHANTMENT.toString())) {
                    result.add(CardType.ENCHANTMENT);
                }
                if (s.equals(CardType.INSTANT.toString())) {
                    result.add(CardType.INSTANT);
                }
                if (s.equals(CardType.LAND.toString())) {
                    result.add(CardType.LAND);
                }
                if (s.equals(CardType.PHENOMENON.toString())) {
                    result.add(CardType.PHENOMENON);
                }
                if (s.equals(CardType.PLANE.toString())) {
                    result.add(CardType.PLANE);
                }
                if (s.equals(CardType.PLANESWALKER.toString())) {
                    result.add(CardType.PLANESWALKER);
                }
                if (s.equals(CardType.SCHEME.toString())) {
                    result.add(CardType.SCHEME);
                }
                if (s.equals(CardType.SORCERY.toString())) {
                    result.add(CardType.SORCERY);
                }
                if (s.equals(CardType.TRIBAL.toString())) {
                    result.add(CardType.TRIBAL);
                }
                if (s.equals(CardType.VANGUARD.toString())) {
                    result.add(CardType.VANGUARD);
                }
            }
        }
        return result;
    }

}
