package com.example.umstest.common;

import com.example.umstest.common.model.Account;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BaseChecker {
    public static void assertNotNullResponse(Response response){
        response.then().log().all().extract().asString();
        assertEquals(200 , response.getStatusCode());
        JsonPath reJsonPath = response.body().jsonPath();
        if(!reJsonPath.getString("success").contains("null")){
            assertTrue(reJsonPath.getBoolean("success"));
        }
        if(!reJsonPath.getString("data").contains("null")){
            assertNotNull(reJsonPath.getJsonObject("data"));
        }
    }

    public static void  CheckChangedAccountInformation(List<Account> accounts, int order, Account checkEditAccount ){
        assertEquals(accounts.get(order).getFullName(), checkEditAccount.getFullName());
        assertEquals(accounts.get(order).getUserName(), checkEditAccount.getUserName());
    }

    public static void assertNullResponse(Response response){
        response.then().log().all().extract().asString();
        assertEquals(400 , response.getStatusCode());
        JsonPath reJsonPath = response.body().jsonPath();
        assertNull(reJsonPath.getJsonObject("data"));
    }


}
