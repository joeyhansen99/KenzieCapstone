package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class CardUpdateRequest {

    @NotEmpty
    @JsonProperty("id")
    private String id;

    // does card have foil finish
    @JsonProperty("foil")
    private boolean foil;

    // does card display full art
    @JsonProperty("fullArt")
    private boolean fullArt;

    // number of this card in collection
    @JsonProperty("quantity")
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CardUpdateRequest{" +
                "id='" + id + '\'' +
                ", foil=" + foil +
                ", fullArt=" + fullArt +
                ", quantity=" + quantity +
                '}';
    }
}
