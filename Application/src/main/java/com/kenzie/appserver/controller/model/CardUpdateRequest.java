package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Card;

import javax.validation.constraints.NotEmpty;

public class CardUpdateRequest {

    @NotEmpty
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("foil")
    private boolean foil; // does card have foil finish
    @JsonProperty("fullArt")
    private boolean fullArt; // does card display full art*/
    @JsonProperty("quantity")
    private int quantity; // number of this card in collection


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

}

//quantity full art and foil
