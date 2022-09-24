package com.example.cards.service;

import com.example.cards.exception.EntityNotFoundException;
import com.example.cards.model.CardOrigin;
import com.example.cards.repository.CardOriginRepository;
import com.example.cards.request.CardOriginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CardOriginService {

    private final CardOriginRepository repository;

    @Autowired
    public CardOriginService(CardOriginRepository repository) {
        this.repository = repository;
    }

    public CardOrigin findById(int id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card origin id ["+ id + "] não foi encontrado"));
    }

    public CardOrigin createCardOrigin(CardOriginRequest cardOriginRequest){
        var cardOrigin = new CardOrigin();

        cardOrigin.setName(cardOriginRequest.getName());
        cardOrigin.setDescription(cardOriginRequest.getDescription());
        cardOrigin.setCreator(cardOriginRequest.getCreator());

        cardOrigin.setCreatedAt(LocalDateTime.now());
        cardOrigin.setUpdatedAt(LocalDateTime.now());

        return repository.save(cardOrigin);
    }

}
