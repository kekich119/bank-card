package com.example.bankcards.controller;

import com.example.bankcards.dto.CardResponseDto;
import com.example.bankcards.dto.SendMoneyDto;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.security.JWT;
import com.example.bankcards.service.CardService;
import com.example.bankcards.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final CardService cardService;
    private final JWT jwtcore;

    public UserController(UserService userService, JWT jwtcore, CardService cardService) {
        this.userService = userService;
        this.jwtcore = jwtcore;
        this.cardService = cardService;
    }

    @GetMapping("/get/cards/hide")
    public List<CardResponseDto> getUserCards(HttpServletRequest request) {
        String token = jwtcore.getToken(request);
        String email = jwtcore.getEmailFromToken(token);

        List<Card> cards = userService.getUserCardsByEmail(email);

        if (cards.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You haven't cards");
        }else {
            return cards.stream()
                    .map(card -> new CardResponseDto(
                            card.getId(),
                            cardService.maskCardNumber(card.getCardNumber()),
                            card.getOwner(),
                            card.getStatus(),
                            card.getBalance(),
                            card.getDateAdd(),
                            card.getDateExpire()

                    ))
                    .toList();
        }


    }

    @GetMapping("/get/cards/full")
    public List<Card> getUserCardFull(HttpServletRequest request) {
        String token = jwtcore.getToken(request);
        String email = jwtcore.getEmailFromToken(token);
        List<Card> cards = userService.getUserCardsByEmail(email);
        return cards;
    }


    @PostMapping("/send-money")
    public ResponseEntity<String> sendMoney(HttpServletRequest request, @RequestBody SendMoneyDto sendMoneyDto) {
        String token = jwtcore.getToken(request);
        String email = jwtcore.getEmailFromToken(token);
        User user = userService.findByEmail(email);
        String name = user.getName();
        String owner = cardService.getOwnerByCardNumber(sendMoneyDto.getCardNumberFrom());
        if (name.equals(owner)) {
            ResponseEntity answer = (cardService.sendMoney(sendMoneyDto.getCardNumberFrom(), sendMoneyDto.getCardNumberTo(), sendMoneyDto.getAmount()));


            return ResponseEntity.status(answer.getStatusCode()).body(answer.getBody().toString());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You not the owner of the card");
        }


    }

}
