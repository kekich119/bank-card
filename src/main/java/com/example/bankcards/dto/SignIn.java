package com.example.bankcards.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SignIn {
    private String username;
    private String password;
}
