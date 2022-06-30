package com.example.umstest.common.resources.account;

import com.example.umstest.common.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AccountJson {
    private int id;
    private String description;
    private Account data;
}
