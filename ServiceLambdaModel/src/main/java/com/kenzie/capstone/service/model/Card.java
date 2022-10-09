package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Card {
    // unique id number
    @JsonProperty("id")
    private String id;
    // name of card
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

    public Card() {
    }

    public Card(String id, String name, String set, int quantity, int cost, List<CardColor> color,
                List<CardType> type, CardRarity rarity) {
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

    public List<CardColor> getColor() {
        return color;
    }

    public void setColor(List<CardColor> color) {
        this.color = color;
    }

    public List<CardType> getType() {
        return type;
    }

    public void setType(List<CardType> type) {
        this.type = type;
    }

    public CardRarity getRarity() {
        return rarity;
    }

    public void setRarity(CardRarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", set='" + set + '\'' +
                ", foil=" + foil +
                ", fullArt='" + fullArt + '\'' +
                ", color='" + quantity + '\'' +
                ", color='" + cost + '\'' +
                ", color='" + color + '\'' +
                ", color='" + type + '\'' +
                ", rarity='" + rarity + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, set, foil, fullArt, quantity, cost, color, type, rarity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card that = (Card) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(set, that.set) &&
                Objects.equals(foil, that.foil) &&
                Objects.equals(fullArt, that.fullArt) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(color, that.color) &&
                Objects.equals(type, that.type) &&
                Objects.equals(rarity, that.rarity);
    }
}
