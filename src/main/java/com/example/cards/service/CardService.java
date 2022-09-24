package com.example.cards.service;

import com.example.cards.model.Card;
import com.example.cards.repository.impl.CardRepositoryImpl;
import com.example.cards.request.CardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepositoryImpl repository;

    @Autowired
    public CardService(CardRepositoryImpl repository) {
        this.repository = repository;
    }

    public Optional<Card> findById(int id){
        return repository.findById(id);
    }

    public Card createCard(CardRequest cardRequest){
        var card = new Card();

        card.setName(cardRequest.getName());
        card.setDescription(cardRequest.getDescription());
        card.setImageUrl(cardRequest.getImageUrl());

        card.setStrength(cardRequest.getStrength());
        card.setSpeed(cardRequest.getSpeed());
        card.setIntellect(cardRequest.getIntellect());
        card.setGear(cardRequest.getGear());
        card.setSkill(cardRequest.getSkill());

        card.setCreatedAt(LocalDateTime.now());
        card.setUpdatedAt(LocalDateTime.now());

        return repository.save(card);

    }
}
