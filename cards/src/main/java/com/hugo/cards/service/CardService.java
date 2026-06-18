package com.hugo.cards.service;

import com.hugo.cards.model.Card;
import com.hugo.cards.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public List<Card> findAllExistingCards() {
        return repository.findAll();
    }

    public List<Card> findAllExistingCardsByCustomerId(long customerId) {
        return repository.findAllCardsByCustomerId(customerId);
    }

    public Card createNewCard(Card card){
        return repository.save(card);
    }
}
