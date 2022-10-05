package com.kenzie.appserver.controller.model;
import com.kenzie.appserver.service.model.Card;
import com.kenzie.appserver.service.model.CardColor;
import com.kenzie.appserver.service.model.CardRarity;
import com.kenzie.appserver.service.model.CardType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.kenzie.capstone.service.model.ExternalCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardResponse {

    @JsonProperty("id")
    private String id;

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

    public List<CardColor> getCardColor() {
        return color;
    }

    public void setCardColor(List<CardColor> color) {
        this.color = color;
    }

    public List<CardType> getCardType() {
        return type;
    }

    public void setCardType(List<CardType> type) {
        this.type = type;
    }

    public CardRarity getCardRarity() {
        return rarity;
    }

    public void setCardRarity(CardRarity rarity) {
        this.rarity = rarity;
    }

    public static CardResponse createCardResponse(Card card) {

        CardResponse cardResponse = new CardResponse();
        cardResponse.setId(card.getId());
        cardResponse.setName(card.getName());
        cardResponse.setSet(card.getSet());
        cardResponse.setFoil(card.isFoil());
        cardResponse.setFullArt(card.isFullArt());
        cardResponse.setQuantity(card.getQuantity());
        cardResponse.setCost(card.getCost());
        cardResponse.setCardColor(card.getCardColor());
        cardResponse.setCardType(card.getCardType());
        cardResponse.setCardRarity(card.getCardRarity());

        return cardResponse;

    }

    public static List<CardResponse> externalToResponse(List<ExternalCard> externalCardList) {
        List<CardResponse> cardResponseList = new ArrayList<>();
        List<CardColor> colorList = Arrays.asList(CardColor.B, CardColor.U, CardColor.G,
                CardColor.R, CardColor.W, CardColor.COLORLESS);
        List<CardType> typeList = Arrays.asList(CardType.ARTIFACT, CardType.CONSPIRACY, CardType.CREATURE,
                CardType.ENCHANTMENT, CardType.INSTANT, CardType.LAND, CardType.PHENOMENON, CardType.PLANE,
                CardType.PLANESWALKER, CardType.SCHEME, CardType.SORCERY, CardType.TRIBAL, CardType.VANGUARD);
        List<CardRarity> rarityList = Arrays.asList(CardRarity.COMMON, CardRarity.UNCOMMON, CardRarity.RARE,
                CardRarity.MYTHIC_RARE);
        for (ExternalCard ec : externalCardList) {
            CardResponse cr = new CardResponse();
            cr.setName(ec.getName());
            cr.setSet(ec.getSet());
            cr.setFoil(false);
            cr.setFullArt(false);
            cr.setQuantity(1);
            List<CardColor> crColorList = new ArrayList<>();
            for (String color : ec.getColors()) {
                for (CardColor c : colorList) {
                    if (c.toString().equals(color)) {
                        crColorList.add(c);
                    } else {
                        crColorList.add(CardColor.COLORLESS);
                    }
                }
            }
            cr.setCardColor(crColorList);
            List<CardType> crTypeList = new ArrayList<>();
            for (String type : ec.getTypes()) {
                for (CardType t : typeList) {
                    if (t.toString().equals(type)) {
                        crTypeList.add(t);
                    }
                }
            }
            cr.setCardType(crTypeList);
            for (CardRarity rarity : rarityList) {
                if (rarity.toString().equals(ec.getRarity())) {
                    cr.setCardRarity(rarity);
                }
            }
            cardResponseList.add(cr);
        }
        return cardResponseList;
    }
}
