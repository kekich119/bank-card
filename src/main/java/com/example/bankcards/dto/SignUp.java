package com.example.bankcards.dto;


import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SignUp {
    private String name;
    @Email
    private String email;
    private String password;
}
