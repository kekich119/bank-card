package com.example.bankcards.controller;

import com.example.bankcards.dto.UserViewDto;
import com.example.bankcards.entity.Card;
import com.example.bankcards.security.JWT;
import com.example.bankcards.service.CardService;
import com.example.bankcards.service.UserService;
import com.example.bankcards.util.RoleType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    @Mock
    private UserService userService;

    @Mock
    private CardService cardService;
    @Mock
    private JWT jwtcore;
    @InjectMocks
    private UserController userController;


    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetCardsHide() throws Exception {

        Card card = new Card(
                "1234567812345678",
                "string",
                "ACTIVE",
                100,
                LocalDate.now(),
                LocalDate.now().plusYears(3)
        );
        card.setId(1L);

        List<Card> cards = List.of(card);

        when(jwtcore.getToken(any())).thenReturn("token");
        when(jwtcore.getEmailFromToken(any())).thenReturn("user@example.com");
        when(userService.getUserCardsByEmail("user@example.com")).thenReturn(cards);

        when(cardService.maskCardNumber("1234567812345678"))
                .thenReturn("**** **** **** 5678");

        mockMvc.perform(get("/user/get/cards/hide"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].cardNumber").value("**** **** **** 5678"))
                .andExpect(jsonPath("$[0].owner").value("string"))
                .andExpect(jsonPath("$[0].status").value("ACTIVE"))
                .andExpect(jsonPath("$[0].balance").value(100));
    }

    @Test
    void testGetCardsShow() throws Exception {
        Card card = new Card(
                "1234567812345678",
                "string",
                "ACTIVE",
                100,
                LocalDate.now(),
                LocalDate.now().plusYears(3)
        );
        card.setId(1L);

        List<Card> cards = List.of(card);

        when(jwtcore.getToken(any())).thenReturn("token");
        when(jwtcore.getEmailFromToken(any())).thenReturn("user@example.com");
        when(userService.getUserCardsByEmail("user@example.com")).thenReturn(cards);

        mockMvc.perform(get("/user/get/cards/full"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].cardNumber").value("1234567812345678"))
                .andExpect(jsonPath("$[0].owner").value("string"))
                .andExpect(jsonPath("$[0].status").value("ACTIVE"))
                .andExpect(jsonPath("$[0].balance").value(100));

    }
}
