package com.example.bankcards.controller;

import com.example.bankcards.dto.UserViewDto;
import com.example.bankcards.security.JWT;
import com.example.bankcards.service.CardService;
import com.example.bankcards.service.UserService;
import com.example.bankcards.util.RoleType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JWT jwt;


    @MockitoBean
    private UserService userService;

    @MockitoBean
    private CardService cardService;

    @Test
    void shouldReturnAllUsers_whenAdmin() throws Exception {

        String token = "fake-token";
        String email = "admin@mail.com";

        when(jwt.getToken(any())).thenReturn(token);
        when(jwt.getEmailFromToken(token)).thenReturn(email);
        when(userService.getRoleByEmail(email)).thenReturn(RoleType.ADMIN);

        when(userService.findAllUserWithoutPassword())
                .thenReturn(List.of(
                        new UserViewDto(1L, "alex@mail.com", "Alex", 1),
                        new UserViewDto(2L, "bob@mail.com", "Bob", 2)
                ));

        mockMvc.perform(get("/admin/get/all/users"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].name").value("Alex"))
                .andExpect((ResultMatcher) jsonPath("$[0].email").value("alex@mail.com"))
                .andExpect((ResultMatcher) jsonPath("$[1].name").value("Bob"))
                .andExpect((ResultMatcher) jsonPath("$[1].email").value("bob@mail.com"));
    }
}