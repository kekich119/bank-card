package com.example.bankcards.dto;

import lombok.Data;

@Data
public class SendMoneyDto {
    private int amount;
    private String cardNumberFrom;
    private String cardNumberTo;
}
