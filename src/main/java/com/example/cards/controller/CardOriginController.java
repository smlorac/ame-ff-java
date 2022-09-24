package com.example.cards.controller;

import com.example.cards.model.CardOrigin;
import com.example.cards.request.CardOriginRequest;
import com.example.cards.service.CardOriginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card-origin")
public class CardOriginController {

    private static final Logger log = LoggerFactory.getLogger(CardOriginController.class);

    private final CardOriginService service;

    @Autowired
    public CardOriginController(CardOriginService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public CardOrigin findCardOriginById(@PathVariable("id") int id){
        log.info("Buscando card origin com id [{}]", id);

        return service.findById(id);
    }

    @PostMapping
    public CardOrigin createCardOrigin(@RequestBody CardOriginRequest cardOriginRequest){
        return service.createCardOrigin(cardOriginRequest);
    }

}
