package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.CardResponse;
import com.kenzie.appserver.controller.model.CardUpdateRequest;
import com.kenzie.appserver.controller.model.CreateCardRequest;
import com.kenzie.appserver.service.CardService;
import com.kenzie.appserver.service.model.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private CardService cardService;

    CardController(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponse> getCard(@PathVariable("cardId") String cardId) throws Exception {
        Card card = cardService.findById(cardId);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        CardResponse cardResponse = CardResponse.createCardResponse(card);
        return ResponseEntity.ok(cardResponse);
    }

    @PostMapping
    public ResponseEntity<CardResponse> addCard(@RequestBody CreateCardRequest createCardRequest) throws Exception {
        Card card = cardService.addNewCard(new Card(
                createCardRequest.getName(),
                createCardRequest.getSet(),
                createCardRequest.isFoil(),
                createCardRequest.isFullArt(),
                1,
                createCardRequest.getCost(),
                createCardRequest.getCardColor(),
                createCardRequest.getCardType(),
                createCardRequest.getCardRarity()));
        CardResponse cardResponse = CardResponse.createCardResponse(card);
        return ResponseEntity.created(URI.create("/cards/" + cardResponse.getId())).body(cardResponse);
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> getAllCards() {
        List<Card> cards = cardService.findAllCards();

        if (cards == null || cards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CardResponse> responseList = new ArrayList<>();
        for (Card card : cards) {
            responseList.add(CardResponse.createCardResponse(card));
        }

        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping
    public ResponseEntity deleteCardById(@PathVariable("cardId") String cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cardId}")
    public ResponseEntity<CardResponse> updateCardById(@RequestBody CardUpdateRequest updateRequest,
                                                       @PathVariable("cardId") String cardId) {

        cardService.updateCard(updateRequest, cardId);
        CardResponse cardResponse = CardResponse.createCardResponse(cardService.findById(cardId));
        return ResponseEntity.ok(cardResponse);
    }


}
