package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.google.common.base.Objects;
@DynamoDBTable(tableName = "Example")
public class CardRecord {
    private String id; // unique id number
    private String name; // name of card
    private String set; // card set
    private boolean foil; // does card have foil finish
    private boolean fullArt; // does card display full art
    private int quantity; // number of this card in collection
    private int cost; // combined mana cost of card
    private CardRecord.Color color; // color of card
    private CardRecord.Type type; // type of card
    private CardRecord.Rarity rarity; // rarity of card
    enum Color {
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
    enum Type {
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
    enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        MYTHIC_RARE
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
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

    @DynamoDBAttribute(attributeName = "quantity")
    public int getQuantity() {
        return quantity;
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

    //SETTERS
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public void setFoil(boolean foil) {
        this.foil = foil;
    }

    public void setFullArt(boolean fullArt) {
        this.fullArt = fullArt;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardRecord that = (CardRecord) o;
        return foil == that.foil && fullArt == that.fullArt && quantity == that.quantity && cost == that.cost && Objects.equal(id, that.id) && Objects.equal(name, that.name) && Objects.equal(set, that.set) && color == that.color && type == that.type && rarity == that.rarity;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, set, foil, fullArt, quantity, cost, color, type, rarity);
    }
}
