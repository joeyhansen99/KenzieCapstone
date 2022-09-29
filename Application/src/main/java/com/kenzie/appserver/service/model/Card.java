package com.kenzie.appserver.service.model;

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

    public CardColor getCardColor() {
        return color;
    }

    public CardType getCardType() {
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
