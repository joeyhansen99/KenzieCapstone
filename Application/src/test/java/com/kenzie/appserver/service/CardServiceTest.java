package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.controller.model.CardUpdateRequest;
import com.kenzie.appserver.repositories.CardRepository;
import com.kenzie.appserver.repositories.model.CardRecord;
import com.kenzie.appserver.service.model.Card;
import com.kenzie.appserver.service.model.CardColor;
import com.kenzie.appserver.service.model.CardRarity;
import com.kenzie.appserver.service.model.CardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class CardServiceTest {
    private CardRepository cardRepository;
    private CardService cardService;
    private CacheStore cacheStore;

    @BeforeEach
    void setup() {
        cardRepository = mock(CardRepository.class);
        cacheStore = mock(CacheStore.class);
        cardService = new CardService(cardRepository, cacheStore);
    }

    @Test
    void findById() {
        // GIVEN
        String id = randomUUID().toString();
        CardRecord record = new CardRecord();
        record.setId(id);
        record.setName("Test Name");
        record.setSet("Test Set");
        record.setQuantity(1);
        record.setCost(5);
        record.setCardColor(CardColor.BLUE);
        record.setCardType(CardType.CREATURE);
        record.setCardRarity(CardRarity.COMMON);
        // WHEN
        when(cardRepository.findById(id)).thenReturn(Optional.of(record));
        Card card = cardService.findById(id);

        // THEN
        Assertions.assertNotNull(record, "object is returned");
        Assertions.assertEquals(card.getId(), record.getId(), "id matches");
        Assertions.assertEquals(card.getName(), record.getName(), "name matches");
        Assertions.assertEquals(card.getSet(), record.getSet(), "set matches");
        Assertions.assertEquals(card.isFoil(), record.isFoil(), "foil matches");
        Assertions.assertEquals(card.isFullArt(), record.isFullArt(), "full art matches");
        Assertions.assertEquals(card.getQuantity(), record.getQuantity(), "quantity matches");
        Assertions.assertEquals(card.getCost(), record.getCost(), "cost matches");
        Assertions.assertEquals(card.getCardColor(), record.getCardColor(), "color matches");
        Assertions.assertEquals(card.getCardType(), record.getCardType(), "type matches");
        Assertions.assertEquals(card.getCardRarity(), record.getCardRarity(), "rarity matches");
    }

    @Test
    void findAllCards() {
        // GIVEN
        CardRecord record1 = new CardRecord();
        record1.setId(randomUUID().toString());
        record1.setName("Test Name 1");
        record1.setSet("Test Set 1");
        record1.setQuantity(1);
        record1.setCost(5);
        record1.setCardColor(CardColor.BLUE);
        record1.setCardType(CardType.CREATURE);
        record1.setCardRarity(CardRarity.COMMON);

        CardRecord record2 = new CardRecord();
        record2.setId(randomUUID().toString());
        record2.setName("Test Name 2");
        record2.setSet("Test Set 2");
        record2.setQuantity(2);
        record2.setCost(10);
        record2.setCardColor(CardColor.RED);
        record2.setCardType(CardType.ARTIFACT);
        record2.setCardRarity(CardRarity.RARE);

        List<CardRecord> recordList = new ArrayList<>();
        recordList.add(record1);
        recordList.add(record2);
        when(cardRepository.findAll()).thenReturn(recordList);

        // WHEN
        List<Card> cards = cardService.findAllCards();

        // THEN
        Assertions.assertNotNull(cards, "The card list is returned");
        Assertions.assertEquals(2, cards.size(), "There are two cards");

        for (Card card : cards) {
            if (Objects.equals(card.getId(), record1.getId())) {
                Assertions.assertEquals(record1.getId(), card.getId(), "id matches");
                Assertions.assertEquals(record1.getName(), card.getName(), "name matches");
                Assertions.assertEquals(record1.getSet(), card.getSet(), "set matches");
                Assertions.assertEquals(record1.getQuantity(), card.getQuantity(), "quantity matches");
                Assertions.assertEquals(record1.getCost(), card.getCost(), "cost matches");
                Assertions.assertEquals(record1.isFoil(), card.isFoil(), "foil matches");
                Assertions.assertEquals(record1.isFullArt(), card.isFullArt(), "full art matches");
                Assertions.assertEquals(record1.getCardColor(), card.getCardColor(), "color matches");
                Assertions.assertEquals(record1.getCardType(), card.getCardType(), "type matches");
                Assertions.assertEquals(record1.getCardRarity(), card.getCardRarity(), "rarity matches");
            } else if (Objects.equals(card.getId(), record2.getId())) {
                Assertions.assertEquals(record2.getId(), card.getId(), "id matches");
                Assertions.assertEquals(record2.getName(), card.getName(), "name matches");
                Assertions.assertEquals(record2.getSet(), card.getSet(), "set matches");
                Assertions.assertEquals(record2.getQuantity(), card.getQuantity(), "quantity matches");
                Assertions.assertEquals(record2.getCost(), card.getCost(), "cost matches");
                Assertions.assertEquals(record2.isFoil(), card.isFoil(), "foil matches");
                Assertions.assertEquals(record2.isFullArt(), card.isFullArt(), "full art matches");
                Assertions.assertEquals(record2.getCardColor(), card.getCardColor(), "color matches");
                Assertions.assertEquals(record2.getCardType(), card.getCardType(), "type matches");
                Assertions.assertEquals(record2.getCardRarity(), card.getCardRarity(), "rarity matches");
            } else {
                Assertions.fail("Card returned that was not in the records!");
            }
        }
    }

    @Test
    void addNewCard() {
        // GIVEN
        Card card = new Card(randomUUID().toString(), "test name", "test set", 1, 2,
                CardColor.BLACK_RED, CardType.LAND, CardRarity.UNCOMMON);
        ArgumentCaptor<CardRecord> cardRecordArgumentCaptor = ArgumentCaptor.forClass(CardRecord.class);

        // WHEN
        Card returnedCard = cardService.addNewCard(card);

        // THEN
        Assertions.assertNotNull(returnedCard);
        verify(cardRepository).save(cardRecordArgumentCaptor.capture());
        CardRecord record = cardRecordArgumentCaptor.getValue();

        Assertions.assertNotNull(record, "The record is returned");
        Assertions.assertEquals(record.getId(), card.getId(), "id matches");
        Assertions.assertEquals(record.getName(), card.getName(), "name matches");
        Assertions.assertEquals(record.getSet(), card.getSet(), "set matches");
        Assertions.assertEquals(record.getQuantity(), card.getQuantity(), "quantity matches");
        Assertions.assertEquals(record.getCost(), card.getCost(), "cost matches");
        Assertions.assertEquals(record.isFoil(), card.isFoil(), "foil matches");
        Assertions.assertEquals(record.isFullArt(), card.isFullArt(), "full art matches");
        Assertions.assertEquals(record.getCardColor(), card.getCardColor(), "color matches");
        Assertions.assertEquals(record.getCardType(), card.getCardType(), "type matches");
        Assertions.assertEquals(record.getCardRarity(), card.getCardRarity(), "rarity matches");
    }

    @Test
    void updateCard() {
        //GIVEN
        Card card = new Card(randomUUID().toString(), "test name", "test set", 1, 2,
                CardColor.BLACK_RED, CardType.LAND, CardRarity.UNCOMMON);
        cardService.addNewCard(card);

        CardUpdateRequest cardUpdateRequest = new CardUpdateRequest();
        cardUpdateRequest.setId(card.getId());
        cardUpdateRequest.setFoil(card.isFoil());
        cardUpdateRequest.setFullArt(card.isFullArt());
        cardUpdateRequest.setQuantity(2);

        //WHEN
        when(cardRepository.existsById(card.getId())).thenReturn(true);
        cardService.updateCard(cardUpdateRequest, card.getId());

        //THEN
        verify(cacheStore).evict(card.getId());

    }

    @Test
    void deleteCard() {
        Card card = new Card(randomUUID().toString(), "test name", "test set", 1, 2,
                CardColor.BLACK_RED, CardType.LAND, CardRarity.UNCOMMON);
        cardService.addNewCard(card);
        cardService.deleteCard(card.getId());

        verify(cardRepository).deleteById(card.getId());
        verify(cacheStore).evict(card.getId());
    }
}