package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.CardResponse;
import com.kenzie.appserver.controller.model.CreateCardRequest;
import com.kenzie.appserver.service.CardService;
import com.kenzie.appserver.service.model.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/cards")
public class CardController {

    private CardService cardService;

    CardController(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<List<String>> getCard(@PathVariable("cardId") String cardId) throws Exception {
        List<String> cards;
        if (cards == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cards);
    }

    @PostMapping
    public ResponseEntity<CardResponse> addCard(@RequestBody CreateCardRequest createCardRequest) throws Exception {
        Card card = new Card(createCardRequest.getName(),
                createCardRequest.getSet(),
                1,
                createCardRequest.getCost(),
                createCardRequest.getCardColor(),
                createCardRequest.getCardType(),
                createCardRequest.getCardRarity());
    }
}
