package com.example.cards.repository.impl;

import com.example.cards.exception.InvalidEntityAttributeException;
import com.example.cards.model.Card;
import com.example.cards.repository.CardRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CardRepositoryImpl implements CardRepository {

    private final List<Card> cards = new ArrayList<>();
    @Override
    public Optional<Card> findById(int id) {
        return cards.stream().filter(card -> card.getId() == id).findFirst();
    }

    @Override
    public Card save(Card card) {
        var cardFound = cards.stream()
                .filter(storedCard -> storedCard.getName().equals(card.getName()))
                .findFirst();

        if (cardFound.isPresent()){
            throw new InvalidEntityAttributeException("Nome [" + card.getName() + "] já existe.");
        }

        card.setId(cards.size() + 1);
        cards.add(card);

        return card;
    }
}
