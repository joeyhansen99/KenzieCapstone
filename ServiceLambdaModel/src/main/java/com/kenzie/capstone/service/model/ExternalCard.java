package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class ExternalCard {

    @JsonProperty("name")
    private String name;

    @JsonProperty("cmc")
    private int cmc;

    @JsonProperty("colors")
    private List<String> colors;

    @JsonProperty("types")
    private List<String> types;

    @JsonProperty("rarity")
    private String rarity;

    @JsonProperty("set")
    private String set;

    @JsonProperty("imageUrl")
    private String imageUrl;

    public ExternalCard() {
    }

    public ExternalCard(String name, int cmc, List<String> colors, List<String> types,
                        String rarity, String set, String imageUrl) {
        this.name = name;
        this.cmc = cmc;
        this.colors = colors;
        this.types = types;
        this.rarity = rarity;
        this.set = set;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCmc() {
        return cmc;
    }

    public void setCmc(int cmc) {
        this.cmc = cmc;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ExternalCard{" +
                "name='" + name + '\'' +
                ", set='" + set + '\'' +
                ", cmc=" + cmc +
                ", colors='" + colors + '\'' +
                ", types='" + types + '\'' +
                ", rarity='" + rarity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExternalCard that = (ExternalCard) o;
        return cmc == that.cmc &&
                Objects.equals(name, that.name) &&
                Objects.equals(set, that.set) &&
                Objects.equals(colors, that.colors) &&
                Objects.equals(types, that.types) &&
                Objects.equals(rarity, that.rarity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, set, cmc, colors, types, rarity);
    }
}
