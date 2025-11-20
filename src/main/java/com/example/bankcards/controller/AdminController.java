package com.example.bankcards.controller;

import com.example.bankcards.dto.DeleteCardDto;
import com.example.bankcards.dto.UserViewDto;
import com.example.bankcards.entity.Card;
import com.example.bankcards.security.JWT;
import com.example.bankcards.service.CardService;
import com.example.bankcards.service.UserService;
import com.example.bankcards.util.RoleType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final JWT jwtcore;
    private final UserService userService;
    private final CardService cardService;

    public AdminController(UserService userService, JWT jwtcore, CardService cardService) {

        this.userService = userService;
        this.jwtcore = jwtcore;
        this.cardService = cardService;
    }


    @GetMapping("/get/all/users")
    public List<UserViewDto> getAllUsers(HttpServletRequest request) {
        String token = jwtcore.getToken(request);
        String email = jwtcore.getEmailFromToken(token);
        RoleType role = userService.getRoleByEmail(email);

        if (role == RoleType.ADMIN) {
            return userService.findAllUserWithoutPassword();  //list users without password
        } else {
            return null;
        }
    }


    @GetMapping("/get/all/cards")
    public List<Card> getAllCards(HttpServletRequest request) {
        String token = jwtcore.getToken(request);
        String email = jwtcore.getEmailFromToken(token);
        RoleType role = userService.getRoleByEmail(email);

        if (role == RoleType.ADMIN) {
            return cardService.findAll();
        } else {
            return null;
        }
    }


    @PostMapping("/manage/card/delete")
    public ResponseEntity<?> manageCardDelete(HttpServletRequest request, @RequestBody DeleteCardDto deleteCardDto) {
        String token = jwtcore.getToken(request);
        String email = jwtcore.getEmailFromToken(token);
        RoleType role = userService.getRoleByEmail(email);
        if (role == RoleType.ADMIN) {
            ResponseEntity<?> response =  cardService.deleteCardByCardNumber(deleteCardDto.getCardNumber());
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("You don't have a license", HttpStatus.FORBIDDEN);
        }
    }




}
