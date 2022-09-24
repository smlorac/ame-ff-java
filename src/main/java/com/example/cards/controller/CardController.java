package com.example.cards.controller;

import com.example.cards.model.Card;
import com.example.cards.request.CardRequest;
import com.example.cards.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService service;

    private static final Logger log = LoggerFactory.getLogger(CardController.class);

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Card findCardById(@PathVariable("id") int id){
        log.info("Buscando card com id {}", id);

        var card = service.findById(id);

        if (card.isPresent()){
            return card.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card não encontrado");
    }

    @PostMapping
    public Card createCard(@RequestBody CardRequest cardRequest){
        log.info("Criando card [{}] " + cardRequest.getId());
        return service.createCard(cardRequest);
    }
}
