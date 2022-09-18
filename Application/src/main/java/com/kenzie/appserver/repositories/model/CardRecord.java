package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.google.common.base.Objects;
import com.kenzie.appserver.service.model.CardRarity;
import com.kenzie.appserver.service.model.CardType;

@DynamoDBTable(tableName = "cards")
public class CardRecord {

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

    public CardRecord() {
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

    public void setCardColor(CardColor color) {
        this.color = color;
    }

    public void setCardType(CardType type) {
        this.type = type;
    }

    public void setCardRarity(CardRarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardRecord that = (CardRecord) o;
        return foil == that.foil && fullArt == that.fullArt && quantity == that.quantity && cost == that.cost &&
                Objects.equal(id, that.id) && Objects.equal(name, that.name) && Objects.equal(set, that.set) &&
                color == that.color && type == that.type && rarity == that.rarity;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, set, foil, fullArt, quantity, cost, color, type, rarity);
    }
}
