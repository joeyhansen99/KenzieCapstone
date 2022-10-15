package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.controller.model.CardUpdateRequest;
import com.kenzie.appserver.repositories.CardRepository;
import com.kenzie.appserver.repositories.model.CardRecord;
import com.kenzie.appserver.service.model.Card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private final CardRepository cardRepository;
    private final CacheStore cache;

    @Autowired
    public CardService(CardRepository cardRepository, CacheStore cache) {
        this.cardRepository = cardRepository;
        this.cache = cache;
    }

    public Card findById(String id) {
        Card result;
        if (cache.get(id) != null) {
            return cache.get(id);
        }
        if (cardRepository.existsById(id)) {
            result = cardRepository
                    .findById(id)
                    .map(card -> new Card(card.getId(), card.getName(), card.getSet(), card.isFoil(), card.isFullArt(),
                            card.getQuantity(), card.getCost(), card.getCardColor(), card.getCardType(),
                            card.getCardRarity()))
                    .orElse(null);
            cache.add(id, result);
            return result;
        }
        return null;
    }

    public Card addNewCard(Card card) {
        CardRecord cardRecord = new CardRecord();
        cardRecord.setId(card.getId());
        cardRecord.setName(card.getName());
        cardRecord.setSet(card.getSet());
        cardRecord.setFoil(card.isFoil());
        cardRecord.setFullArt(card.isFullArt());
        cardRecord.setQuantity(card.getQuantity());
        cardRecord.setCost(card.getCost());
        cardRecord.setCardColor(card.getCardColor());
        cardRecord.setCardType(card.getCardType());
        cardRecord.setCardRarity(card.getCardRarity());
        cardRepository.save(cardRecord);
        return card;
    }

    public void updateCard(CardUpdateRequest updateRequest) {
        if (cardRepository.existsById(updateRequest.getId())) {
            Card card = findById(updateRequest.getId());
            CardRecord cardRecord = new CardRecord();
            cardRecord.setId(card.getId());
            cardRecord.setName(card.getName());
            cardRecord.setSet(card.getSet());
            cardRecord.setFoil(updateRequest.isFoil());
            cardRecord.setFullArt(updateRequest.isFullArt());
            cardRecord.setQuantity(updateRequest.getQuantity());
            cardRecord.setCost(card.getCost());
            cardRecord.setCardColor(card.getCardColor());
            cardRecord.setCardType(card.getCardType());
            cardRecord.setCardRarity(card.getCardRarity());
            cardRepository.save(cardRecord);
            cache.evict(card.getId());
        } else {
            System.out.println("Update failed - card does not exist!");
        }
    }

    public void deleteCard(String id) {
        try {
            cardRepository.deleteById(id);
            cache.evict(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Card> findAllCards() {
        List<Card> cards = new ArrayList<>();
        Iterable<CardRecord> cardRecordIterable = cardRepository.findAll();
        for (CardRecord record : cardRecordIterable) {
            cards.add(new Card(record.getId(), record.getName(), record.getSet(), record.isFoil(), record.isFullArt(),
                    record.getQuantity(), record.getCost(), record.getCardColor(), record.getCardType(),
                    record.getCardRarity()));
        }
        return cards;
    }

}
