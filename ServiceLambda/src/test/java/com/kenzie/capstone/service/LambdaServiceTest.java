package com.kenzie.capstone.service;

import com.kenzie.capstone.service.converter.CardConverter;
import com.kenzie.capstone.service.dao.CardDao;

import com.kenzie.capstone.service.model.Card;
import com.kenzie.capstone.service.model.CardColor;
import com.kenzie.capstone.service.model.CardRarity;
import com.kenzie.capstone.service.model.CardRecord;
import com.kenzie.capstone.service.model.CardType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LambdaServiceTest {

    private CardDao cardDao;
    private LambdaService lambdaService;

    @BeforeAll
    void setup() {
        this.cardDao = mock(CardDao.class);
        this.lambdaService = new LambdaService(cardDao);
    }

    @Test
    void getAllCards() {
        List<CardRecord> cardRecordList = new ArrayList<>();
        List<Card> expected = new ArrayList<>();

        List<CardColor> cardRecordColor1 = new ArrayList<>(List.of(CardColor.COLORLESS));
        List<CardType> cardRecordType1 = new ArrayList<>(List.of(CardType.ARTIFACT));

        CardRecord cardRecord1 = new CardRecord();
        cardRecord1.setId("235-THB-EN");
        cardRecord1.setName("Nyx Lotus");
        cardRecord1.setSet("THB");
        cardRecord1.setFoil(false);
        cardRecord1.setFullArt(false);
        cardRecord1.setQuantity(2);
        cardRecord1.setCost(4);
        cardRecord1.setColor(cardRecordColor1);
        cardRecord1.setType(cardRecordType1);
        cardRecord1.setRarity(CardRarity.RARE);
        cardRecordList.add(cardRecord1);

        List<CardColor> cardRecordColor2 = new ArrayList<>(List.of(CardColor.B, CardColor.G));
        List<CardType> cardRecordType2 = new ArrayList<>(List.of(CardType.INSTANT));
        CardRecord cardRecord2 = new CardRecord();
        cardRecord2.setId("242-STX-EN");
        cardRecord2.setName("Tend the Pests");
        cardRecord2.setSet("STX");
        cardRecord2.setFoil(false);
        cardRecord2.setFullArt(false);
        cardRecord2.setQuantity(4);
        cardRecord2.setCost(2);
        cardRecord2.setColor(cardRecordColor2);
        cardRecord2.setType(cardRecordType2);
        cardRecord2.setRarity(CardRarity.UNCOMMON);
        cardRecordList.add(cardRecord2);

        List<CardColor> cardRecordColor3 = new ArrayList<>(List.of(CardColor.U));
        List<CardType> cardRecordType3 = new ArrayList<>(List.of(CardType.INSTANT));
        CardRecord cardRecord3 = new CardRecord();
        cardRecord3.setId("059-STX-EN");
        cardRecord3.setName("Test of Talents");
        cardRecord3.setSet("STX");
        cardRecord3.setFoil(true);
        cardRecord3.setFullArt(false);
        cardRecord3.setQuantity(1);
        cardRecord3.setCost(2);
        cardRecord3.setColor(cardRecordColor3);
        cardRecord3.setType(cardRecordType3);
        cardRecord3.setRarity(CardRarity.UNCOMMON);
        cardRecordList.add(cardRecord3);

        when(cardDao.getAllCards()).thenReturn(cardRecordList);

        for (CardRecord cardRecord : cardRecordList) {
            expected.add(CardConverter.fromRecordtoCard(cardRecord));
        }

        // WHEN
        List<Card> allCards = this.lambdaService.getAllCards();

        // THEN
        verify(cardDao, times(1));

        assertEquals(3, allCards.size(), "The correct number of cards are returned");
        for (int i = 0; i < 1; i++) {
            assertEquals(allCards.get(i).getId(), expected.get(i).getId(), "Card id matches");
            assertEquals(allCards.get(i).getName(), expected.get(i).getName(), "Card name matches");
            assertEquals(allCards.get(i).getSet(), expected.get(i).getSet(), "Card set matches");
            assertEquals(allCards.get(i).isFoil(), expected.get(i).isFoil(), "Card foil matches");
            assertEquals(allCards.get(i).isFullArt(), expected.get(i).isFullArt(), "Card fullArt matches");
            assertEquals(allCards.get(i).getQuantity(), expected.get(i).getQuantity(), "Card quantity matches");
            assertEquals(allCards.get(i).getCost(), expected.get(i).getCost(), "Card cost matches");
            assertEquals(allCards.get(i).getColor(), expected.get(i).getColor(), "Card color matches");
            assertEquals(allCards.get(i).getType(), expected.get(i).getType(), "Card type matches");
            assertEquals(allCards.get(i).getRarity(), expected.get(i).getRarity(), "Card rarity matches");
        }
    }

}
