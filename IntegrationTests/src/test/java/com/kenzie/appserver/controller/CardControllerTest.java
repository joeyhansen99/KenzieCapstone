package com.kenzie.appserver.controller;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.kenzie.appserver.controller.model.CardUpdateRequest;
import com.kenzie.appserver.controller.model.CreateCardRequest;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.service.CardService;
import com.kenzie.appserver.service.model.Card;
import com.kenzie.appserver.service.model.CardColor;
import com.kenzie.appserver.service.model.CardRarity;
import com.kenzie.appserver.service.model.CardType;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.andreinc.mockneat.MockNeat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class CardControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    CardService cardService;

    private final MockNeat mockNeat = MockNeat.threadLocal();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String CARD_TABLE_NAME = "cards";
    private final DynamoDB client =
            new DynamoDB(AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build());

    @Test
    public void cardTable_exists() {
        for (Table table : client.listTables()) {
            if (table.getTableName().equals(CARD_TABLE_NAME)) {
                return;
            }
        }
        fail(String.format("Did not find expected table, '%s'", CARD_TABLE_NAME));
    }

    @Test
    public void getCard() throws Exception{

        // GIVEN
        String id = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String set = mockNeat.strings().valStr();
        boolean foil = mockNeat.bools().get();
        boolean fullArt = mockNeat.bools().get();
        int quantity = mockNeat.ints().get();
        int cost = mockNeat.ints().get();
        List<CardColor> color = new ArrayList<>(List.of(CardColor.R));
        List<CardType> type = new ArrayList<>(List.of(CardType.VANGUARD));
        CardRarity rarity = CardRarity.RARE;

        // WHEN
        Card card = new Card(id, name, set, foil, fullArt, quantity, cost, color, type, rarity);
        Card persistedCard = cardService.addNewCard(card);

        // THEN
        mvc.perform(get("/cards/{cardId}", persistedCard.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id")
                        .value(is(id)))
                .andExpect(jsonPath("name")
                        .value(is(name)))
                .andExpect(jsonPath("set")
                        .value(is(set)))
                .andExpect(jsonPath("foil")
                        .value(is(foil)))
                .andExpect(jsonPath("fullArt")
                        .value(is(fullArt)))
                .andExpect(jsonPath("quantity")
                        .value(is(quantity)))
                .andExpect(jsonPath("cost")
                        .value(is(cost)))
                .andExpect(status().isOk());
    }

    @Test
    public void getCard_CardDoesNotExist() throws Exception{
        String id = mockNeat.strings().get();

        mvc.perform(get("/cards/{cardId}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addCard() throws Exception{
        Card card = createCardHelper();

        CreateCardRequest example = new CreateCardRequest();
        example.setId(card.getId());

        mvc.perform(post("/cards/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(example)))
                .andExpect(jsonPath("id")
                        .value(is(card.getId())))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllCards() throws Exception{
        // GIVEN
        Card card1 = createCardHelper();
        Card card2 = createCardHelper();
        // WHEN
        cardService.addNewCard(card1);
        Thread.sleep(1000);
        cardService.addNewCard(card2);
        Thread.sleep(1000);
        // THEN
        mvc.perform(get("/cards/all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void deleteCardById() throws Exception{
        // GIVEN
        Card card = createCardHelper();
        cardService.addNewCard(card);
        // WHEN
        Thread.sleep(1000);
        mvc.perform(delete("/cards/{cardId}", card.getId()).accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNoContent());
        assertThat(cardService.findById(card.getId())).isNull();
    }

    @Test
    public void updateCardById() throws Exception {
        // GIVEN
        Card card = createCardHelper();
        cardService.addNewCard(card);
        Thread.sleep(1000);
        CardUpdateRequest cardUpdateRequest = new CardUpdateRequest();
        cardUpdateRequest.setId(card.getId());
        cardUpdateRequest.setQuantity(card.getQuantity());
        cardUpdateRequest.setFoil(true);
        cardUpdateRequest.setFullArt(true);
        // WHEN
        mvc.perform(patch("/cards/{cardId}", card.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cardUpdateRequest)))
                        .andExpect(status().isOk());
        // THEN
        assertTrue(cardService.findById(card.getId()).isFoil());
        assertTrue(cardService.findById(card.getId()).isFullArt());
    }

    public Card createCardHelper() {
        String id = randomUUID().toString();
        String name = mockNeat.strings().get();
        String set = mockNeat.strings().get();
        int quantity = 1;
        int cost = 10;
        List<CardColor> color = new ArrayList<>();
        color.add(mockNeat.constant(CardColor.R).get());
        color.add(mockNeat.constant(CardColor.G).get());
        color.add(mockNeat.constant(CardColor.B).get());
        List<CardType> type = new ArrayList<>();
        type.add(mockNeat.constant(CardType.VANGUARD).get());
        CardRarity rarity = mockNeat.constant(CardRarity.RARE).get();

        return new Card(id, name, set, quantity, cost, color, type, rarity);
    }

}