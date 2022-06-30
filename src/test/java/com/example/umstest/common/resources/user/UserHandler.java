package com.example.umstest.common.resources.user;


import com.example.umstest.common.BaseHelper;
import com.example.umstest.common.enums.UserEnum;
import com.example.umstest.common.model.User;
import lombok.Data;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Data
public class UserHandler {
    private final List<UserJson> userJsonList;

    public UserHandler()  {
        try {
            userJsonList = Arrays.asList(BaseHelper.getJsonResourceData("data/user/user.json", UserJson[].class));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public User findByName(UserEnum userEnum){
        return Objects.requireNonNull(userJsonList
                        .stream()
                        .filter(userRegistration -> userRegistration.getId() == userEnum.getId())
                        .findFirst()
                        .orElse(null))
                .getData();
    }
}
