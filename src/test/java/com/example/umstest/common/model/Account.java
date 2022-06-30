package com.example.umstest.common.model;

import com.example.umstest.common.enums.CardType;
import com.example.umstest.common.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String id;
    private String phoneNumber;
    private String userName;
    private String fullName;
    private String address;
    private String birthdate;
    private String password;
    private String sex;
    private String email;
    private String faceUrl;
    private String fontCard;
    private String backCard;
    private String idCardNo;
    private String idCardType;
    private String issueCardDate;
    private String expiredCardDate;
    private Boolean active;
}
