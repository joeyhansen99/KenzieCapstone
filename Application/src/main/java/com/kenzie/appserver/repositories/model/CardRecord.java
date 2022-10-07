package com.kenzie.appserver.repositories.model;
import com.kenzie.appserver.service.model.CardColor;
import com.kenzie.appserver.service.model.CardRarity;
import com.kenzie.appserver.service.model.CardType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import com.google.common.base.Objects;

import java.util.List;

@DynamoDBTable(tableName = "cards")
public class CardRecord {

    // unique id number
    private String id;
    // name of card
    private String name;
    // card set
    private String set;
    // does card have foil finish
    private boolean foil;
    // does card display full art
    private boolean fullArt;
    // number of this card in collection
    private int quantity;
    // combined mana cost of card
    private int cost;
    // color of card
    private List<CardColor> color;
    // type of card
    private List<CardType> type;
    // rarity of card
    private CardRarity rarity;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "name")
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
    public List<CardColor> getCardColor() {
        return color;
    }

    @DynamoDBAttribute(attributeName = "type")
    public List<CardType> getCardType() {
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

    public void setCardColor(List<CardColor> color) {
        this.color = color;
    }

    public void setCardType(List<CardType> type) {
        this.type = type;
    }

    public void setCardRarity(CardRarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
