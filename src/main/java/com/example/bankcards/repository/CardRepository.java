package com.example.bankcards.repository;

import com.example.bankcards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findTopByOrderByIdDesc();

    List<Card> findCardsByOwner(String owner);

    Card findByCardNumber(String cardNumber);

    Card findCardByCardNumber(String cardNumber);

    void removeCardByCardNumber(String cardNumber);
    @Transactional
    void deleteCardByCardNumber(String cardNumber);
}
