package com.example.umstest.common.resources.user;

import com.example.umstest.common.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserJson {
    private int id;
    private String description;
    private User data;
}
