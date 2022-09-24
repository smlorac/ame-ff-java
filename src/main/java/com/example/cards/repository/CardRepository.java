package com.example.cards.repository;

import com.example.cards.model.Card;

import java.util.Optional;

public interface CardRepository {

    Optional<Card> findById(int id);

    Card save(Card card);
}
