package com.example.cards.repository;

import com.example.cards.model.CardOrigin;

import java.util.Optional;

public interface CardOriginRepository {

    Optional<CardOrigin> findById(int id);

    CardOrigin save(CardOrigin origin);
}
