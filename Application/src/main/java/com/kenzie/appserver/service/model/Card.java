package com.kenzie.appserver.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.UUID;

@DynamoDBTable(tableName = "cards")
public class Card {

    private String id; // unique id number
    private String name; // name of card
    private String set; // card set
    private boolean foil; // does card have foil finish
    private boolean fullArt; // does card display full art
    private int quantity; // number of this card in collection
    private int cost; // combined mana cost of card
    private CardColor color; // color of card
    private CardType type; // type of card
    private CardRarity rarity; // rarity of card

    public Card() {
    }

    public Card(String id, String name, String set, int quantity, int cost, CardColor color,
                CardType type, CardRarity rarity) {
        this.id = id;
        this.name = name;
        this.set = set;
        this.foil = false;
        this.fullArt = false;
        this.quantity = quantity;
        this.cost = cost;
        this.color = color;
        this.type = type;
        this.rarity = rarity;
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "quantity")
    public int getQuantity() {
        return quantity;
    }

    @DynamoDBRangeKey(attributeName = "name")
    public String getName() {
        return name;
    }

    @DynamoDBAttribute(attributeName = "set")
    public String getSet() {
        return set;
    }

    @DynamoDBAttribute(attributeName = "foil")
    public boolean isFoil() {
        return foil;
    }

    @DynamoDBAttribute(attributeName = "fullArt")
    public boolean isFullArt() {
        return fullArt;
    }

    @DynamoDBAttribute(attributeName = "cost")
    public int getCost() {
        return cost;
    }

    @DynamoDBAttribute(attributeName = "color")
    public CardColor getCardColor() {
        return color;
    }

    @DynamoDBAttribute(attributeName = "type")
    public CardType getCardType() {
        return type;
    }

    @DynamoDBAttribute(attributeName = "rarity")
    public CardRarity getCardRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
