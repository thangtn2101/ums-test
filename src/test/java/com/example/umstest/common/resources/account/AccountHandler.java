package com.example.umstest.common.resources.account;

import com.example.umstest.common.BaseHelper;
import com.example.umstest.common.enums.AccountEnum;
import com.example.umstest.common.model.Account;
import lombok.Data;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Data
public class AccountHandler {
    private final List<AccountJson> accountJsonList;

    public AccountHandler(){
        try {
            accountJsonList = Arrays.asList(BaseHelper.getJsonResourceData("data/account/account.json", AccountJson[].class));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Account findByName(AccountEnum accountEnum){
        return Objects.requireNonNull(accountJsonList
                        .stream()
                        .filter(accountJson -> accountJson.getId() == accountEnum.getId())
                        .findFirst()
                        .orElse(null))
                .getData();
    }
}
