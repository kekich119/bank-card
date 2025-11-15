package com.example.bankcards.util;


import lombok.Data;

@Data
public class ConstStatus {
    private final String ACTIVE = "ACTIVE";
    private final String INACTIVE = "INACTIVE";
    private final String DELETED = "DELETED";
    private final String BLOCKED = "BLOCKED";


}
