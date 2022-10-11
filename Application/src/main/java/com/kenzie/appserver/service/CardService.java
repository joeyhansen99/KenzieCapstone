package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.controller.model.CardUpdateRequest;
import com.kenzie.appserver.repositories.CardRepository;
import com.kenzie.appserver.repositories.model.CardRecord;
import com.kenzie.appserver.service.model.Card;

import com.kenzie.appserver.service.model.CardColor;
import com.kenzie.appserver.service.model.CardRarity;
import com.kenzie.appserver.service.model.CardType;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.ExternalCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CardService {

    @Autowired
    private final CardRepository cardRepository;
    private final CacheStore cache;
    private final LambdaServiceClient lambdaServiceClient;

    @Autowired
    public CardService(CardRepository cardRepository, CacheStore cache, LambdaServiceClient lambdaServiceClient) {
        this.cardRepository = cardRepository;
        this.cache = cache;
        this.lambdaServiceClient = lambdaServiceClient;
    }

    public Card findById(String id) {
        return cardRepository
                .findById(id)
                .map(card -> new Card(card.getId(), card.getName(), card.getSet(), card.isFoil(), card.isFullArt(),
                        card.getQuantity(), card.getCost(), card.getCardColor(), card.getCardType(),
                        card.getCardRarity()))
                .orElse(null);
    }

    // add a new card when user picks it from a provided list after name search
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

    // returns a list of cards from MTG API based on search parameter (name)
    public List<ExternalCard> returnCardList(String name) {
        return lambdaServiceClient.getCardData(name);
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
        List<com.kenzie.capstone.service.model.Card> cardList = lambdaServiceClient.getAllCardsData();
        List<Card> endList = new ArrayList<>();
        for (com.kenzie.capstone.service.model.Card c : cardList) {
            endList.add(convertLambdaModelToApp(c));
        }
        return endList;
    }

//    public List<Card> findAllCards() {
//        List<Card> cards = new ArrayList<>();
//        Iterable<CardRecord> cardRecordIterable = cardRepository.findAll();
//        for (CardRecord record : cardRecordIterable) {
//            cards.add(new Card(record.getId(), record.getName(), record.getSet(), record.getQuantity(),
//                    record.getCost(), record.getCardColor(), record.getCardType(), record.getCardRarity()));
//        }
//        return cards;
//    }

    public static Card convertLambdaModelToApp(com.kenzie.capstone.service.model.Card card) {
        return new Card(
                card.getId(),
                card.getName(),
                card.getSet(),
                card.isFoil(),
                card.isFullArt(),
                card.getQuantity(),
                card.getCost(),
                lambdaColorToApp(card.getColor()),
                lambdaTypeToApp(card.getType()),
                lambdaRarityToApp(card.getRarity()));
    }

    public static List<CardColor> lambdaColorToApp(List<com.kenzie.capstone.service.model.CardColor> colors) {
        List<CardColor> endList = new ArrayList<>();
        for (com.kenzie.capstone.service.model.CardColor color : colors) {
            for (CardColor c : CardColor.values()) {
                if (color.toString().equals(c.toString())) {
                    endList.add(c);
                }
            }
        }
        return endList;
    }

    public static List<CardType> lambdaTypeToApp(List<com.kenzie.capstone.service.model.CardType> types) {
        List<CardType> endList = new ArrayList<>();
        for (com.kenzie.capstone.service.model.CardType type : types) {
            for (CardType t : CardType.values()) {
                if (type.toString().equals(t.toString())) {
                    endList.add(t);
                }
            }
        }
        return endList;
    }

    public static CardRarity lambdaRarityToApp(com.kenzie.capstone.service.model.CardRarity rarity) {
        for (CardRarity r : CardRarity.values()) {
            if (r.toString().equals(rarity.toString())) {
                return r;
            }
        }
        return null;
    }
}
