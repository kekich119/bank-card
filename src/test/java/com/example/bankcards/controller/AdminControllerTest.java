package com.example.bankcards.controller;

import com.example.bankcards.dto.UserViewDto;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
    @Mock
    private CardService cardService;
    @Mock
    private UserService userService;
    @Mock
    private JWT jwtcore;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void testGetAllUsersUnAuth() throws Exception {
        mockMvc.perform(get("/admin/get/all/users")).andExpect(status().isForbidden());
    }

    @Test
    void testGetAllUsersAuth() throws Exception {

        when(jwtcore.getToken(any())).thenReturn("fake_token");
        when(jwtcore.getEmailFromToken("fake_token")).thenReturn("user@example.com");
        when(userService.getRoleByEmail("user@example.com")).thenReturn(RoleType.ADMIN);

        mockMvc.perform(get("/admin/get/all/users"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllCardsUnAuth() throws Exception {
        mockMvc.perform(get("/admin/get/all/cards")).andExpect(status().isForbidden());
    }

    @Test
    void testGetAllCardsAuth() throws Exception {
        when(jwtcore.getToken(any())).thenReturn("fake_token");
        when(jwtcore.getEmailFromToken("fake_token")).thenReturn("user@example.com");
        when(userService.getRoleByEmail("user@example.com")).thenReturn(RoleType.ADMIN);

        mockMvc.perform(get("/admin/get/all/cards")).andExpect(status().isOk());
    }

    @Test
    void testDeleteCardUnAuth() throws Exception {
        mockMvc.perform(post("/admin/manage/card/delete").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "cardNumber": "1234123412341234"
                                    }
                                """))
                .andExpect(status().isForbidden());

    }


    @Test
    void testDeleteCardAuth() throws Exception {

        when(jwtcore.getToken(any())).thenReturn("fake_token");
        when(jwtcore.getEmailFromToken("fake_token")).thenReturn("user@example.com");
        when(userService.getRoleByEmail("user@example.com")).thenReturn(RoleType.ADMIN);

        when(cardService.deleteCardByCardNumber("1189 0014 1230 0001"))
                .thenReturn((ResponseEntity) new ResponseEntity<>("Card deleted", HttpStatus.OK));

        mockMvc.perform(post("/admin/manage/card/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "cardNumber": "1189 0014 1230 0001"
                                }
                                """))
                .andExpect(status().isOk());
    }

}