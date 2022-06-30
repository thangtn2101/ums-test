package com.example.umstest.common.profile;

import com.example.umstest.ApiTest;
import com.example.umstest.common.model.User;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProfileCaseTemplate extends ApiTest {

    public Response userLogin(User user)  {
        Gson gson = new Gson();
        return given().contentType(ContentType.JSON)
                .body(gson.toJson(user))
                .when()
                .post("/api/v1/user/login")
                .then()
                .log().ifValidationFails()
                .extract().response();
    }
    public String loginToGetToken(User user)  {
            return userLogin(user)
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body("success", equalTo(true))
                    .extract().response()
                    .jsonPath()
                    .getString("data.access_token");

    }
}
