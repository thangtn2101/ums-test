package com.example.umstest.common.account;

import com.example.umstest.ApiTest;
import com.example.umstest.common.model.Account;
import com.example.umstest.common.model.User;
import com.example.umstest.common.profile.ProfileCaseTemplate;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountCaseTemplate extends ApiTest {
    ProfileCaseTemplate profileCaseTemplate = new ProfileCaseTemplate();
     public AccountCaseTemplate(User user) {
         setup();
         token = profileCaseTemplate.loginToGetToken(user);
     }
    public Response getAllAccountList(){
        return given()
                .auth().oauth2(token)
                .when()
                .get("/api/v1/accounts/getAlls")
                .andReturn();
    }
    public Response editAccount(String id, Account account)  {
         if(account.getId() != null)
             account.setId(null);
         if(account.getActive() != null)
             account.setActive(null);
        Gson gson = new Gson();
        return given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(gson.toJson(account))
                .when()
                .put("/api/v1/accounts/{id}")
                .andReturn();
    }


}
