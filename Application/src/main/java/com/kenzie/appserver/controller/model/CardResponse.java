package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardResponse {
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
    private CreateCardRequest.Color color; // color of card

    @JsonProperty("type")
    private CreateCardRequest.Type type; // type of card

    @JsonProperty("rarity")
    private CreateCardRequest.Rarity rarity; // rarity of card

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

    public CreateCardRequest.Color getColor() {
        return color;
    }

    public void setColor(CreateCardRequest.Color color) {
        this.color = color;
    }

    public CreateCardRequest.Type getType() {
        return type;
    }

    public void setType(CreateCardRequest.Type type) {
        this.type = type;
    }

    public CreateCardRequest.Rarity getRarity() {
        return rarity;
    }

    public void setRarity(CreateCardRequest.Rarity rarity) {
        this.rarity = rarity;
    }
}
