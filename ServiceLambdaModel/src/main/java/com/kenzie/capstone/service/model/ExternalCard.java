package com.kenzie.capstone.service.model;

import java.util.Objects;

public class ExternalCard {

    private String name;
    private String set;
    private int cmc;
    private String colors;
    private String types;
    private String rarity;

    public ExternalCard() {
    }

    public ExternalCard(String name, String set, int cmc, String colors, String types, String rarity) {
        this.name = name;
        this.set = set;
        this.cmc = cmc;
        this.colors = colors;
        this.types = types;
        this.rarity = rarity;
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

    public int getCmc() {
        return cmc;
    }

    public void setCmc(int cmc) {
        this.cmc = cmc;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
