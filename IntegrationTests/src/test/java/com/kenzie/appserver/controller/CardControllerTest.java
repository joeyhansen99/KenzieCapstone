package com.kenzie.appserver.controller;
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

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.is;

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

    @Test
    public void getCard() throws Exception{
        //GIVEN
        Card card = createCardHelper();
        //WHEN
        Card test = cardService.addNewCard(card);
        //THEN
        mvc.perform(get("/cards/{cardId}", test.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id")
                        .value(is(card.getId())))
                .andExpect(jsonPath("name")
                        .value(is(card.getName())))
                .andExpect(jsonPath("set")
                        .value(is(card.getSet())))
                .andExpect(jsonPath("quantity")
                        .value(is(card.getQuantity())))
                .andExpect(jsonPath("cost")
                        .value(is(card.getCost())))
                .andExpect(jsonPath("color")
                        .value(is(card.getCardColor())))
                .andExpect(jsonPath("type")
                        .value(is(card.getCardType())))
                .andExpect(jsonPath("rarity")
                        .value(is(card.getCardRarity())))
                .andExpect(status().isOk());
    }

    @Test
    public void getCard_CardDoesNotExist() throws Exception{
        String id = mockNeat.strings().get();

        mvc.perform(get("/cards/{cardId}",id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addCard() throws Exception{
        Card card1 = createCardHelper();

        CreateCardRequest example = new CreateCardRequest();
        example.setId(card1.getId());

        mvc.perform(post("/cards/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(example)))
                .andExpect(jsonPath("id")
                        .value(is(card1.getId())))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllCards() throws Exception{
        // GIVEN
        Card card1 = createCardHelper();
        Card card2 = createCardHelper();
        // WHEN
        Card test = cardService.addNewCard(card1);
        Card test1 = cardService.addNewCard(card2);
        // THEN
        mvc.perform(get("/cards/all")).andExpect(status().isOk());
    }

    @Test
    public void deleteCardById() throws Exception{
        // GIVEN
        Card card1 = createCardHelper();
        Card test = cardService.addNewCard(card1);
        // WHEN
        mvc.perform(delete("/cards/", test.getId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNoContent());
        assertThat(cardService.findById(test.getId())).isNull();
    }

    @Test
    public void updateCardById() throws Exception {
        // GIVEN
        Card card = createCardHelper();
        cardService.addNewCard(card);
        CardUpdateRequest cardUpdateRequest = new CardUpdateRequest();
        cardUpdateRequest.setId(mockNeat.strings().get());
        cardUpdateRequest.setQuantity(mockNeat.ints().get());
        // WHEN
        mvc.perform(put("/cards")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cardUpdateRequest)))
                // THEN
                .andExpect(jsonPath("id").value(is(cardUpdateRequest.getId())))
                .andExpect(jsonPath("quantity").value(is(cardUpdateRequest.getQuantity())))
                .andExpect(status().isOk());
    }

    public Card createCardHelper() {
        String id = mockNeat.strings().get();
        String name = mockNeat.strings().get();
        String set = mockNeat.strings().get();
        //boolean foil = mockNeat.bools().get();
        //boolean fullArt = mockNeat.bools().get();
        int quantity = mockNeat.ints().get();
        int cost = mockNeat.ints().get();
        List<CardColor> color = new ArrayList<>();
        color.add(mockNeat.constant(CardColor.R).get());
        List<CardType> type = new ArrayList<>();
        type.add(mockNeat.constant(CardType.ARTIFACT).get());
        CardRarity rarity = mockNeat.constant(CardRarity.COMMON).get();

        return new Card(id, name, set, quantity, cost, color, type, rarity);
    }

}