package com.example.bankcards.controller;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.security.JWT;
import com.example.bankcards.service.CardService;
import com.example.bankcards.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/cards")
    public List<Card> getUserCards(HttpServletRequest request) {
        String token = jwtcore.getToken(request);
        String email = jwtcore.getEmailFromToken(token);
        return userService.getUserCardsByEmail(email);
    }

    @PostMapping("/send-money")
    public ResponseEntity<String> sendMoney(HttpServletRequest request, @RequestParam int amount, String cardNumberFrom, String cardNumberTo) {
        String token = jwtcore.getToken(request);
        String email = jwtcore.getEmailFromToken(token);
        User user = userService.findByEmail(email);
        String name = user.getName();
        String owner = cardService.getOwnerByCardNumber(cardNumberFrom);
        if (name.equals(owner)) {
             ResponseEntity answer = (cardService.sendMoney(cardNumberFrom, cardNumberTo, amount));


            return ResponseEntity.status(answer.getStatusCode()).body(answer.getBody().toString());
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You not the owner of the card");
        }


    }

}
