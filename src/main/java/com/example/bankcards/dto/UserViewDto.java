package com.example.bankcards.dto;

import lombok.Data;

@Data
public class UserViewDto {
    private long id;
    private String email;
    private String name;
    private int roleId;

    public UserViewDto(long id, String email, String name, int roleId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.roleId = roleId;
    }
}
