package com.kenzie.appserver.controller.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.CardColor;
import com.kenzie.appserver.service.model.CardRarity;
import com.kenzie.appserver.service.model.CardType;

import javax.validation.constraints.NotEmpty;

public class CreateCardRequest {
    @NotEmpty
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("set")
    private String set; // card set

    @JsonProperty("foil")
    private boolean foil; // does card have foil finish

    @JsonProperty("fullArt")
    private boolean fullArt; // does card display full art

    @JsonProperty("quantity")
    private int quantity; // number of this card in collection

    @JsonProperty("cost")
    private int cost; // combined mana cost of card

    @JsonProperty("color")
    public CardColor color; // color of card

    @JsonProperty("type")
    public CardType type; // type of card

    @JsonProperty("rarity")
    public CardRarity rarity; // rarity of card

    public CreateCardRequest (){

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

    public CardColor getCardColor() {
        return color;
    }

    public void setCardColor(CardColor color) {
        this.color = color;
    }

    public CardType getCardType() {
        return type;
    }

    public void setCardType(CardType type) {
        this.type = type;
    }

    public CardRarity getCardRarity() {
        return rarity;
    }

    public void setCardRarity(CardRarity rarity) {
        this.rarity = rarity;
    }

}