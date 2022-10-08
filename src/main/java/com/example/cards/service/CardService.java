package com.example.cards.service;

import com.example.cards.exception.EntityNotFoundException;
import com.example.cards.model.Card;
import com.example.cards.repository.CardOriginRepository;
import com.example.cards.repository.CardRepository;
import com.example.cards.request.CardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardOriginRepository cardOriginRepository;

    @Autowired
    public CardService(CardRepository cardRepository, CardOriginRepository cardOriginRepository) {
        this.cardRepository = cardRepository;
        this.cardOriginRepository = cardOriginRepository;
    }

    public Card findById(int id){
        return this.cardRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Card id [" + id + " ] not found."));
    }

    public Card createCard(CardRequest cardRequest){
        var cardOrigin = cardOriginRepository.findById((long) cardRequest.getOriginId())
                .orElseThrow(() -> new EntityNotFoundException("Card origin id ["+ cardRequest.getOriginId() + "] not found" ));

        var card = new Card();

        card.setName(cardRequest.getName());
        card.setDescription(cardRequest.getDescription());
        card.setImageUrl(cardRequest.getImageUrl());
        card.setStrength(cardRequest.getStrength());
        card.setGear(cardRequest.getGear());
        card.setSkill(cardRequest.getSkill());
        card.setSpeed(cardRequest.getSpeed());
        card.setIntellect(cardRequest.getIntellect());
        card.setOrigin(cardOrigin);
        card.setCreatedAt(LocalDateTime.now());
        card.setUpdatedAt(LocalDateTime.now());

        return cardRepository.save(card);
    }

    public List<Card> findAll(){
        return this.cardRepository.findAll();
    }

    public Card update(long id, CardRequest request){
        var card = this.findById((int) id);

        card.setName(request.getName());
        card.setDescription(request.getDescription());
        card.setImageUrl(request.getImageUrl());
        card.setStrength(request.getStrength());
        card.setGear(request.getGear());
        card.setSkill(request.getSkill());
        card.setSpeed(request.getSpeed());
        card.setIntellect(request.getIntellect());
        card.setUpdatedAt(LocalDateTime.now());

        if (card.getOrigin() == null || card.getOrigin().getId() != request.getOriginId()){
            var origin = cardOriginRepository.findById((long) request.getOriginId()).orElseThrow(() -> new EntityNotFoundException("Card origin id [" + request.getOriginId() + "] not found"));

            card.setOrigin(origin);
        }

        return cardRepository.save(card);
    }

    public void delete(long id){
        var card = this.findById((int) id);
        cardRepository.delete(card);
    }
}
