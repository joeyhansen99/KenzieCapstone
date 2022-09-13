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
    public Color color; // color of card
    public Type type; // type of card
    public Rarity rarity; // rarity of card

    public enum Color {
        BLACK,
        BLUE,
        GREEN,
        RED,
        WHITE,
        BLACK_BLUE,
        BLACK_GREEN,
        BLACK_RED,
        BLACK_WHITE,
        BLUE_GREEN,
        BLUE_RED,
        BLUE_WHITE,
        GREEN_RED,
        GREEN_WHITE,
        RED_WHITE,
        THREE_COLOR,
        COLORLESS
    }

    public enum Type {
        ARTIFACT,
        CONSPIRACY,
        CREATURE,
        ENCHANTMENT,
        INSTANT,
        LAND,
        PHENOMENON,
        PLANE,
        PLANESWALKER,
        SCHEME,
        SORCERY,
        TRIBAL,
        VANGUARD
    }

    public enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        MYTHIC_RARE
    }

    public Card() {
    }

    public Card(String name, String set, boolean foil, boolean fullArt, int quantity, int cost, Card.Color color,
                Card.Type type, Card.Rarity rarity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.set = set;
        this.foil = foil;
        this.fullArt = fullArt;
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
    public Color getColor() {
        return color;
    }

    @DynamoDBAttribute(attributeName = "type")
    public Type getType() {
        return type;
    }

    @DynamoDBAttribute(attributeName = "rarity")
    public Rarity getRarity() {
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
