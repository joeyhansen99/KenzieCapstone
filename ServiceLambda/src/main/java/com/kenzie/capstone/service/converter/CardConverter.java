package com.kenzie.capstone.service.converter;

import com.kenzie.capstone.service.model.Card;
import com.kenzie.capstone.service.model.CardRecord;

public class CardConverter {

    //CODE CITE: Jordan helped me with this.
    public static Card fromRecordtoCard(CardRecord record) {
        Card card = new Card();
        card.setId(record.getId());
        card.setName(record.getName());
        card.setSet(record.getSet());
        card.setFoil(record.isFoil());
        card.setFullArt(record.isFullArt());
        card.setQuantity(record.getQuantity());
        card.setCost(record.getCost());
        card.setColor(record.getCardColor());
        card.setType(record.getCardType());
        card.setRarity(record.getCardRarity());
        return card;
    }

}
