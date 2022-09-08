package com.kenzie.appserver.service.model;

public class Card {

    private String id; // unique id number
    private String name; // name of card
    private String set; // card set
    private boolean foil; // does card have foil finish
    private boolean fullArt; // does card display full art
    private int quantity; // number of this card in collection
    private int cost; // combined mana cost of card
    private Color color; // color of card
    private Type type; // type of card
    private Rarity rarity; // rarity of card

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

    public Card() {
    }

    public Card(String id, String name, String set, boolean foil, boolean fullArt, int quantity, int cost, Color color,
                Type type, Rarity rarity) {
        this.id = id;
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

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

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
