package com.example.umstest.common.enums;

import lombok.Getter;

@Getter
public enum AccountEnum {
    ACCOUNT_CHECKING(0 );
    private final int id;
    AccountEnum(int id) {
        this.id = id;
    }
}
