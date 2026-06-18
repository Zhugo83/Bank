package com.hugo.cards.controller;

import com.hugo.cards.config.CardConfig;
import com.hugo.cards.dto.CustomerDTO;
import com.hugo.cards.model.Card;
import com.hugo.cards.model.Properties;
import com.hugo.cards.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;
    private final CardConfig cardConfig;

    public CardController(CardService cardService, CardConfig cardConfig) {
        this.cardService = cardService;
        this.cardConfig = cardConfig;
    }


    @GetMapping()
    public ResponseEntity<List<Card>> getAllCards(){
        List<Card> cards = cardService.findAllExistingCards();
        if (cards.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping("/details/my-cards")
    public ResponseEntity<List<Card>> getCardsDetails(@RequestBody CustomerDTO request){
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Card> cards = cardService.findAllExistingCardsByCustomerId(request.getCustomerId());
        if (cards.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping()
    public Card createCard(@RequestBody Card card) {
        return cardService.createNewCard(card);
    }

    @GetMapping("/details/properties")
    public ResponseEntity<Properties> getPropertiesDetails(){
        Properties properties = new Properties(
                cardConfig.getMsg(),
                cardConfig.getBuildVersion(),
                cardConfig.getMailDetails(),
                cardConfig.getActiveBranches()
        );
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}
