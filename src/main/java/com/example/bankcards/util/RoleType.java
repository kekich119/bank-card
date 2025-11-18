package com.example.bankcards.util;

public enum RoleType {
    BANNED(0),
    USER(1),
    ADMIN(2);

    private final int code;

    RoleType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static RoleType fromCode(int code) {
        return switch (code) {
            case 0 -> BANNED;
            case 1 -> USER;
            case 2 -> ADMIN;
            default -> throw new IllegalArgumentException("Invalid role id: " + code);
        };
    }
}