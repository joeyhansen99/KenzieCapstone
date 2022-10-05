package com.kenzie.appserver.service.model;

import java.util.List;

public class Card {

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

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getSet() {
        return set;
    }

    public boolean isFoil() {
        return foil;
    }

    public boolean isFullArt() {
        return fullArt;
    }

    public int getCost() {
        return cost;
    }

    public List<CardColor> getCardColor() {
        return color;
    }

    public List<CardType> getCardType() {
        return type;
    }

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
