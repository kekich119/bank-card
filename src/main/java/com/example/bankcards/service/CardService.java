package com.example.bankcards.service;


import com.example.bankcards.entity.Card;
import com.example.bankcards.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card save(Card card) {
        return cardRepository.save(card);
    }


    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public String createNumberCard() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String number1 = String.format("%04d", random.nextInt(10000));
        String number2 = String.format("%04d", random.nextInt(10000));

        sb.append("1189 " + number1 + " " + number2 + " " + incrementLast4Symbol());
        return sb.toString();
    }


    public String incrementLast4Symbol() {
        Optional<Card> card = cardRepository.findTopByOrderByIdDesc();

        int last4Number = card.map(c ->
                Integer.parseInt(c.getCardNumber().substring(c.getCardNumber().length() - 4))
        ).orElse(0);

        int increment = (last4Number + 1) % 10000;

        return String.format("%04d", increment);
    }

    public ResponseEntity<String> sendMoney(String numberCardFrom, String numberCardTo, int amount) {
        Card cardFrom = cardRepository.findByCardNumber(numberCardFrom);
        Card cardTo = cardRepository.findByCardNumber(numberCardTo);
        if (cardFrom.getBalance() > amount) {
            cardTo.setBalance(cardTo.getBalance() + amount);
            cardFrom.setBalance(cardFrom.getBalance() - amount);
            cardRepository.save(cardFrom);
            cardRepository.save(cardTo);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("fail, you don't have enough money for translation", HttpStatus.BAD_REQUEST);
        }

    }

    public String getOwnerByCardNumber(String cardNumber) {
        Card card = cardRepository.findCardByCardNumber(cardNumber);
        return card.getOwner();
    }

    public ResponseEntity<?> deleteCardByCardNumber(String cardNumber) {
        cardRepository.deleteCardByCardNumber( cardNumber );
        return new ResponseEntity<>("success delete", HttpStatus.OK);
    }


}
