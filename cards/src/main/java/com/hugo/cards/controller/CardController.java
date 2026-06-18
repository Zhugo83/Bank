package com.hugo.cards.controller;

import com.hugo.cards.model.Card;
import com.hugo.cards.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccount(@RequestBody Card card) {
        cardService.addCard(card);
    }

    @GetMapping("/card/{customer-id}")
    public ResponseEntity<Optional<Card>> getAllAccountsByCustomer(
            @PathVariable("customer-id") Integer customerId
    ){
        return ResponseEntity.ok(cardService.findAllCardsByCustomer(customerId));
    }
}
