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

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService service;

    private static final Logger log = LoggerFactory.getLogger(CardController.class);

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public Card findCardById(@PathVariable("id") int id){

        log.info("Buscando card com id {}", id);
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Card createCard(@RequestBody CardRequest cardRequest){
        log.info("Criando carta: [{}]", cardRequest);
        return service.createCard(cardRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Card> listCards(){
        log.info("Buscando todas as cartas");
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public Card updateCard(@PathVariable("id") long id, @RequestBody CardRequest request){
        log.info("Atualizando carta com id [{}]", id);
        return service.update(id, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public void deleteCard(@PathVariable("id") long id){
        log.info("Excluindo carta com id [{}]", id);
        service.delete(id);
    }
}
