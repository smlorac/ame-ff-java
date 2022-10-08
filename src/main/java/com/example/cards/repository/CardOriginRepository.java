package com.example.cards.repository;

import com.example.cards.model.CardOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardOriginRepository extends JpaRepository<CardOrigin, Long> {

}
