package com.example.cards.controller;

import com.example.cards.model.CardOrigin;
import com.example.cards.request.CardOriginRequest;
import com.example.cards.service.CardOriginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card-origin")
public class CardOriginController {

    private static final Logger log = LoggerFactory.getLogger(CardOriginController.class);

    private final CardOriginService service;

    @Autowired
    public CardOriginController(CardOriginService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public CardOrigin findCardOriginById(@PathVariable("id") long id){
        log.info("Buscando origem com id : {}", id);
        return service.findById((int) id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CardOrigin createCardOrigin(@RequestBody CardOriginRequest cardOriginRequest){
        log.info("Inserindo a origem : {}", cardOriginRequest);
        return service.createCardOrigin(cardOriginRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<CardOrigin> findAllCardOrigins(){
        log.info("Buscando todas as origens");
        return service.listAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public CardOrigin updateCardOrigin(@PathVariable("id") long id, @RequestBody CardOriginRequest request){
        log.info("Atualizando card origin com id {}", id);
        return service.update(id, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public void deleteCardOrigin(@PathVariable("id") long id){
        log.info("Deletando card origin com id [{}]", id);
        service.delete((int) id);
    }

}
