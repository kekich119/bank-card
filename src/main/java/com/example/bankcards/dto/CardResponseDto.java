package com.example.bankcards.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CardResponseDto {


    public CardResponseDto(long id, String cardNumber, String owner, String status,
                           int balance, LocalDate dateAdd, LocalDate dateExpire) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.owner = owner;
        this.status = status;
        this.balance = balance;
        this.dateAdd = dateAdd;
        this.dateExpire = dateExpire;
    }

    private long id;
    private String cardNumber;
    private String owner;
    private String status;
    private int balance;
    private LocalDate dateAdd;
    private LocalDate dateExpire;


}
