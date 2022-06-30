package com.example.umstest.common.media;

import com.example.umstest.ApiTest;
import com.example.umstest.common.BaseChecker;
import com.example.umstest.common.enums.FileType;
import com.example.umstest.common.enums.MediaEnum;
import com.example.umstest.common.enums.UserEnum;
import com.example.umstest.common.model.Media;
import com.example.umstest.common.model.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class MediaAutomationTest extends ApiTest {

    private String photoName;
    Media mediaAvaFile = mediaHandler.findByName(MediaEnum.AVATA_FILE);

    //Khởi tạo user Đăng Nhập
    User availableUser = userHandler.findByName(UserEnum.SECONDARY_USER);
    MediaCaseTemplate mediaCaseTemplate = new MediaCaseTemplate(availableUser);
    @Test
    public void successUploadImageTest() throws URISyntaxException {
        Response response = mediaCaseTemplate.upload(mediaAvaFile, FileType.AVATAR);
        BaseChecker.assertNotNullResponse(response);

        //Get image name after upload from photo path
        photoName = mediaCaseTemplate.getImageName(response);
    }

    @Test(priority = 1)
    public void mediaGetFileImageTest(){
        mediaCaseTemplate.getFileImage(mediaAvaFile.getBucketName() ,photoName)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
