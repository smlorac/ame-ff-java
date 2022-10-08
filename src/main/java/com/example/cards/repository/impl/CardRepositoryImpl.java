package com.example.cards.repository.impl;

import com.example.cards.exception.ApplicationException;
import com.example.cards.exception.InvalidEntityAttributeException;
import com.example.cards.model.Card;
import com.example.cards.model.CardOrigin;
import com.example.cards.repository.CardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CardRepositoryImpl implements CardRepository {

    //private final List<Card> cards = new ArrayList<>();

    private static final Logger log = LogManager.getLogger(CardRepositoryImpl.class);

    private final ConnectionFactory factory;

    public CardRepositoryImpl(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Card> findById(int id) {

        String query = "SELECT * FROM card where id = ?";

        try (Connection connection = factory.getConnection()){
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setInt(1, id);

                st.execute();

                ResultSet rs = st.getResultSet();

                if (rs.next()){
                    Card card = new Card();
                    CardOrigin cardOrigin = new CardOrigin();

                    card.setId(rs.getInt("id"));
                    card.setName(rs.getString("name"));
                    card.setDescription(rs.getString("description"));
                    card.setImageUrl(rs.getString("image_url"));
                    card.setStrength(rs.getInt("strength"));
                    card.setSpeed(rs.getInt("speed"));
                    card.setGear(rs.getInt("gear"));
                    card.setIntellect(rs.getInt("intellect"));
                    card.setSkill(rs.getInt("skill"));
                    card.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    card.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

                    cardOrigin.setId(rs.getInt("origin_id"));
                    card.setOrigin(cardOrigin);

                    return Optional.of(card);
                }
            }
        } catch (SQLException e) {
            log.error("{}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }

        //return cards.stream().filter(card -> card.getId() == id).findFirst();
        return Optional.empty();
    }

    @Override
    public Card save(Card card) {

        String insertQuery = "INSERT INTO card (name, description, image_url, strength, speed, skill, gear, intellect, created_at, updated_at, origin_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = factory.getConnection()){
            try (PreparedStatement st = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)){
                st.setString(1, card.getName());
                st.setString(2, card.getDescription());
                st.setString(3, card.getImageUrl());
                st.setInt(4, card.getStrength());
                st.setInt(5, card.getSpeed());
                st.setInt(6, card.getSkill());
                st.setInt(7, card.getGear());
                st.setInt(8, card.getIntellect());
                st.setTimestamp(9, Timestamp.valueOf(card.getCreatedAt()));
                st.setTimestamp(10, Timestamp.valueOf(card.getUpdatedAt()));
                st.setNull(11, Types.INTEGER);

                st.execute();

                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()){
                    card.setId(rs.getInt(1));

                    return card;
                }
            }
        } catch (SQLException e){
            log.error("{}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }

//        var cardFound = cards.stream()
//                .filter(storedCard -> storedCard.getName().equals(card.getName()))
//                .findFirst();
//
//        if (cardFound.isPresent()){
//            throw new InvalidEntityAttributeException("Nome [" + card.getName() + "] já existe.");
//        }
//
//        card.setId(cards.size() + 1);
//        cards.add(card);

        throw new ApplicationException("Card could not be saved: " + card);
    }
}
