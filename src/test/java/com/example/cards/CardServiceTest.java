package com.example.cards;

import com.example.cards.exception.EntityNotFoundException;
import com.example.cards.model.CardOrigin;
import com.example.cards.repository.CardOriginRepository;
import com.example.cards.repository.CardRepository;
import com.example.cards.request.CardRequest;
import com.example.cards.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CardServiceTest {

    @Mock
    CardOriginRepository originRepository;

    @Mock
    CardRepository cardRepository;

    @InjectMocks
    CardService service;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Retornar erro ao criar card sem origin")
    void shouldReturnErrorOnCreateCardWhenOriginNotFound(){

        when(originRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.createCard(new CardRequest()));
        verify(originRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve criar o card")
    void shouldSaveCardOnCreate(){
        var origin = new CardOrigin();
        origin.setId(1);

        when(originRepository.findById(any())).thenReturn(Optional.of(origin));
        when(cardRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var cardRequest = new CardRequest();
        cardRequest.setName("Nome");
        cardRequest.setDescription("Descrição");
        cardRequest.setImageUrl("URL");
        cardRequest.setStrength(100);
        cardRequest.setGear(20);
        cardRequest.setSkill(50);
        cardRequest.setSpeed(60);
        cardRequest.setIntellect(120);

        var card = service.createCard(cardRequest);

        assertEquals(card.getName(), cardRequest.getName());
        assertEquals(card.getDescription(), cardRequest.getDescription());
        assertEquals(card.getImageUrl(), cardRequest.getImageUrl());
        assertEquals(card.getStrength(), cardRequest.getStrength());
        assertEquals(card.getGear(), cardRequest.getGear());
        assertEquals(card.getSkill(), cardRequest.getSkill());
        assertEquals(card.getSpeed(), cardRequest.getSpeed());
        assertEquals(card.getIntellect(), cardRequest.getIntellect());
    }
}
