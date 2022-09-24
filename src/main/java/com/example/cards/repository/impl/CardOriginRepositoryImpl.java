package com.example.cards.repository.impl;

import com.example.cards.exception.InvalidEntityAttributeException;
import com.example.cards.model.CardOrigin;
import com.example.cards.repository.CardOriginRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CardOriginRepositoryImpl implements CardOriginRepository {

    private final List<CardOrigin> cardOrigins = new ArrayList<>();

    @Override
    public Optional<CardOrigin> findById(int id) {
        return cardOrigins.stream()
                .filter(cardOrigin -> cardOrigin.getId() == id)
                .findFirst();
    }

    @Override
    public CardOrigin save(CardOrigin origin) {
        var cardOriginFound = cardOrigins.stream()
                .filter(storedOrigin -> storedOrigin.getName().equals(origin.getName()))
                .findFirst();

        if (cardOriginFound.isPresent()){
            throw new InvalidEntityAttributeException("Card Origin com nome ["+ origin.getName() + "] já existe");
        }

        origin.setId(cardOrigins.size() + 1);
        cardOrigins.add(origin);

        return origin;
    }
}
