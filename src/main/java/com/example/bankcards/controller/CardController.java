package com.example.bankcards.controller;

import com.example.bankcards.entity.Card;
import com.example.bankcards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;
    @Autowired
    private JavaMailSender mailSender;

    public CardController(CardService cardService) {

        this.cardService = cardService;
    }

    @PostMapping("/create")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        cardService.save(card);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }


    @GetMapping
    public void sendEmail() {
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("lololix468@gmail.com");
        mail.setTo("kekich108@gmail.com");
        mail.setSubject("New Card");
        mail.setText("Your new card has been created");

        mailSender.send(mail);


    }

}
