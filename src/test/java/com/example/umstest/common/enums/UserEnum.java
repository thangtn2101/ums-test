package com.example.umstest.common.enums;

import lombok.Getter;

@Getter
public enum UserEnum {
    ADMIN_USER(1 ),
    SECONDARY_USER(2),
    INVALID_USER(3);
    private final int id;
    UserEnum(int id) {
        this.id = id;
    }
}
