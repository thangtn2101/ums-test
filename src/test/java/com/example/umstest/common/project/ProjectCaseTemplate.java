package com.example.umstest.common.project;

import com.example.umstest.ApiTest;
import com.example.umstest.common.model.Project;
import com.example.umstest.common.model.User;
import com.example.umstest.common.profile.ProfileCaseTemplate;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ProjectCaseTemplate extends ApiTest {
    ProfileCaseTemplate profileCaseTemplate = new ProfileCaseTemplate();
    public ProjectCaseTemplate(User user) {
        setup();
        token = profileCaseTemplate.loginToGetToken(user);
    }

    public Response getAllProject() {
        return given()
                .auth().oauth2(token)
                .when()
                .get("/api/v1/projects/getAll")
                .andReturn();
    }


    public Response editProject(Project project){
        JSONObject requestParams = new JSONObject();
        try {
            requestParams.put("name", project.getName());
            requestParams.put("image", project.getImage());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .pathParam("id", project.getId())
                .body(requestParams.toString())
                .when()
                .put("/api/v1/projects/{id}")
                .andReturn();
    }
}
