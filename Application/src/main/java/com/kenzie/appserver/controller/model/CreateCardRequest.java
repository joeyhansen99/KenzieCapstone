package com.kenzie.appserver.controller.model;
import com.kenzie.appserver.service.model.CardColor;
import com.kenzie.appserver.service.model.CardRarity;
import com.kenzie.appserver.service.model.CardType;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class CreateCardRequest {

    @NotEmpty
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    // card set
    @JsonProperty("set")
    private String set;

    // does card have foil finish
    @JsonProperty("foil")
    private boolean foil;

    // does card display full art
    @JsonProperty("fullArt")
    private boolean fullArt;

    // number of this card in collection
    @JsonProperty("quantity")
    private int quantity;

    // combined mana cost of card
    @JsonProperty("cost")
    private int cost;

    // color of card
    @JsonProperty("color")
    private List<CardColor> color;

    // type of card
    @JsonProperty("type")
    private List<CardType> type;

    // rarity of card
    @JsonProperty("rarity")
    private CardRarity rarity;

    public CreateCardRequest() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public boolean isFoil() {
        return foil;
    }

    public void setFoil(boolean foil) {
        this.foil = foil;
    }

    public boolean isFullArt() {
        return fullArt;
    }

    public void setFullArt(boolean fullArt) {
        this.fullArt = fullArt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<CardColor> getCardColor() {
        return color;
    }

    public void setCardColor(List<CardColor> color) {
        this.color = color;
    }

    public List<CardType> getCardType() {
        return type;
    }

    public void setCardType(List<CardType> type) {
        this.type = type;
    }

    public CardRarity getCardRarity() {
        return rarity;
    }

    public void setCardRarity(CardRarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
        return "CreateCardRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", set='" + set + '\'' +
                ", foil=" + foil +
                ", fullArt=" + fullArt +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", color=" + color +
                ", type=" + type +
                ", rarity=" + rarity +
                '}';
    }
}
