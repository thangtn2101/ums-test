package com.example.umstest.common.enums;

import lombok.Getter;

@Getter
public enum MediaEnum {
    AVATA_FILE(1 ),
    CCCD_FILE(2);
    private final int id;
    MediaEnum(int id) {
        this.id = id;
    }
}
