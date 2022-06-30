package com.example.umstest;

import com.example.umstest.common.resources.account.AccountHandler;
import com.example.umstest.common.resources.media.MediaHandler;
import com.example.umstest.common.resources.user.UserHandler;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;


public class ApiTest {

    protected String token;
    private final String BASE_URI = "http://10.30.1.110";
    private final int PORT = 8080;
    protected AccountHandler accountHandler = new AccountHandler();
    protected UserHandler userHandler = new UserHandler();
    protected MediaHandler mediaHandler = new MediaHandler();


    @BeforeClass
    public void setup(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;

    }
}
