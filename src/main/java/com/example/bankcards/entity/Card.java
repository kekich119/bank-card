package com.example.bankcards.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.Currency;
@Data
@Entity
@Table(name = "card")
public class Card {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cardNumber;

    private String owner;

    private String status;

    private int balance;

    public Card(String cardNumber, String owner, String status, int balance) {
        this.cardNumber = cardNumber;
        this.owner = owner;
        this.status = status;
        this.balance = balance;


    }
}
