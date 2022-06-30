package com.example.umstest.common.profile;

import com.example.umstest.common.BaseChecker;
import com.example.umstest.common.enums.UserEnum;
import com.example.umstest.common.model.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.notNullValue;

public class ProfileAutomationTest extends ProfileCaseTemplate{
    private final User availableUser = userHandler.findByName(UserEnum.SECONDARY_USER);
    private final User unavailableUser = userHandler.findByName(UserEnum.INVALID_USER);

    @Test
    public void successLoginTest(){
        Response response = this.userLogin(this.availableUser);
        BaseChecker.assertNotNullResponse(response);
        response
                .then()
                .assertThat()
                .body("data.access_token", notNullValue());
    }
    @Test
    public void loginWithInvalidUsernameTest() {
        User user = new User("InvalidUsername",this.availableUser.getPassword());
        BaseChecker.assertNullResponse(this.userLogin(user));
    }
    @Test
    public void loginWithInvalidPasswordTest(){
        User user = new User(this.availableUser.getUsername(), "InvalidPassword");
        BaseChecker.assertNullResponse(this.userLogin(user));
    }
    @Test
    public void loginWithEmptyUsernameTest(){
        User user = new User(" ", this.availableUser.getPassword());
        BaseChecker.assertNullResponse(this.userLogin(user));
    }
    @Test
    public void loginWithEmptyPasswordTest(){
        User user = new User(this.availableUser.getUsername(), "");
        BaseChecker.assertNullResponse(this.userLogin(user));
    }
    @Test
    public void loginWithNullValueTest(){
        BaseChecker.assertNullResponse(this.userLogin(this.unavailableUser));
    }
}
