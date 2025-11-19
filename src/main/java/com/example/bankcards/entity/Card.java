package com.example.bankcards.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "card")
@NoArgsConstructor
public class Card {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cardNumber;

    private String owner;

    private String status;

    private int balance;

    private LocalDate dateAdd;

    @Column(name = "expiration")
    private LocalDate dateExpire;

    public Card(String cardNumber, String owner, String status, int balance, LocalDate dateAdd, LocalDate dateExpire) {
        this.cardNumber = cardNumber;
        this.owner = owner;
        this.status = status;
        this.balance = balance;
        this.dateAdd = dateAdd;
        this.dateExpire = dateExpire;


    }


}
