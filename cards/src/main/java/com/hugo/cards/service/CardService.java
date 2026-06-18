package com.hugo.cards.service;

import com.hugo.cards.model.Card;
import com.hugo.cards.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void addCard(Card card) {
        cardRepository.save(card);
    }

    public void editCard(Card card) {
        cardRepository.save(card);
    }

    public void deleteCard(Card card) {
        cardRepository.delete(card);
    }

    public Optional<Card> findAllCardsByCustomer(Integer id) {
        return cardRepository.findById(id);
    }
}
