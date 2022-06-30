package com.example.umstest.common.media;

import com.example.umstest.ApiTest;
import com.example.umstest.common.enums.FileType;
import com.example.umstest.common.model.Media;
import com.example.umstest.common.model.User;
import com.example.umstest.common.profile.ProfileCaseTemplate;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MediaCaseTemplate extends ApiTest {
    ProfileCaseTemplate profileCaseTemplate = new ProfileCaseTemplate();
    public MediaCaseTemplate(User user) {
        setup();
        token = profileCaseTemplate.loginToGetToken(user);
    }

    public Response upload(Media media, FileType type) throws URISyntaxException {
        URL url = this.getClass().getClassLoader().getResource(media.getDataResourceFile());
        assert url != null;
        File imageFile = new File(url.toURI());
        System.out.println("file ne" + imageFile);
        Map<String, ?> queryParam = new HashMap<>(){{
            put("bucketName", media.getBucketName());
            put("typeFile", type);
        }};
        return given()
                .auth().oauth2(token)
                .contentType("multipart/form-data")
                .queryParams(queryParam)
                .multiPart("file", imageFile, "image/jpeg")
                .when()
                .post("/api/v1/media/private");
    }

    public Response getFileImage(String bucketName, String fileName) {
        return given()
                .auth().oauth2(token)
                .pathParam("bucketName", bucketName)
                .pathParam("fileName", fileName)
                .when()
                .get("/api/v1/media/public/{bucketName}/{fileName}");
    }

    public String getImageName(Response response){
        String photoPath = response.jsonPath().getString("data");
        return photoPath.split("/")[2];
    }
}
