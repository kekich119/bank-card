package com.example.bankcards.controller;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.security.JWT;
import com.example.bankcards.service.CardService;
import com.example.bankcards.service.UserService;
import com.example.bankcards.util.RoleType;
import com.example.bankcards.util.SchedulerCard;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;
    @Autowired
    private JavaMailSender mailSender; // for next time and upgrade proj
    private final JWT jwtcore;
    private final UserService userService;

    public CardController(CardService cardService,  JWT jwtcore, UserService userService) {
        this.jwtcore = jwtcore;
        this.cardService = cardService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCard(HttpServletRequest request) {

        String token = jwtcore.getToken(request);
        String owner = jwtcore.getNameFromToken(token);
        String cardNumber = cardService.createNumberCard();
        Card card = new Card(cardNumber, owner, "ACTIVE",0, LocalDate.now(), LocalDate.now().plusYears(2) );


        cardService.save(card);
        return new ResponseEntity<>("your card has created", HttpStatus.CREATED);
    }


    // на будущее для подтверждения почты пользователя
//    @GetMapping
//    public void sendEmail() {
//        final SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setFrom("Email");
//        mail.setTo("Email");
//        mail.setSubject("New Card");
//        mail.setText("Your new card has been created");
//
//
//        mailSender.send(mail);
//
//
//    }












}
