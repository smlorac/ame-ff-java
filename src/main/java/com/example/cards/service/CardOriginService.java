package com.example.cards.service;

import com.example.cards.exception.EntityNotFoundException;
import com.example.cards.model.CardOrigin;
import com.example.cards.repository.CardOriginRepository;
import com.example.cards.request.CardOriginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardOriginService {

    private final CardOriginRepository cardOriginRepository;

    @Autowired
    public CardOriginService(CardOriginRepository cardOriginRepository) {
        this.cardOriginRepository = cardOriginRepository;
    }

    public CardOrigin findById(int id){
        return this.cardOriginRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Card origin id [ "+id+" not found]"));
    }

    public CardOrigin createCardOrigin(CardOriginRequest cardOriginRequest){
        var cardOrigin = new CardOrigin();

        cardOrigin.setName(cardOriginRequest.getName());
        cardOrigin.setDescription(cardOriginRequest.getDescription());
        cardOrigin.setCreator(cardOriginRequest.getCreator());

        cardOrigin.setCreatedAt(LocalDateTime.now());
        cardOrigin.setUpdatedAt(LocalDateTime.now());

        return cardOriginRepository.save(cardOrigin);
    }

    public List<CardOrigin> listAll(){
        return this.cardOriginRepository.findAll();
    }

    public CardOrigin update(long id, CardOriginRequest request){
        var cardOrigin = this.findById((int) id);

        cardOrigin.setName(request.getName());
        cardOrigin.setDescription(request.getDescription());
        cardOrigin.setCreator(request.getCreator());
        cardOrigin.setUpdatedAt(LocalDateTime.now());

        return cardOriginRepository.save(cardOrigin);
    }

    public void delete(int id){
        var cardOrigin = this.findById(id);
        cardOriginRepository.delete(cardOrigin);
    }

}
