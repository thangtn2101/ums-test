package com.example.umstest.common.project;

import com.example.umstest.ApiTest;
import com.example.umstest.TestUtil;
import com.example.umstest.common.BaseChecker;
import com.example.umstest.common.enums.FileType;
import com.example.umstest.common.enums.MediaEnum;
import com.example.umstest.common.enums.UserEnum;
import com.example.umstest.common.media.MediaCaseTemplate;
import com.example.umstest.common.model.Media;
import com.example.umstest.common.model.Project;
import com.example.umstest.common.model.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectAutomationFlowTest extends ApiTest {
    private int projectOrderNumber;
    private Project originalProject;
    private  Project newProject = new Project("Chee");

    private final User adminUser = userHandler.findByName(UserEnum.ADMIN_USER);

    //Login with admin user
    private ProjectCaseTemplate projectCaseTemplate = new ProjectCaseTemplate(adminUser);

    @Test(priority = 1, description = "Lấy thông tin ID của Project")
    public void getAllProjectTest() {
        Response response =  projectCaseTemplate.getAllProject();
        BaseChecker.assertNotNullResponse(response);

        //Get project list
        List<Project> projects = Arrays.asList(response.body().jsonPath().getObject("$", Project[].class));

        //Set origin Project to check
        projectOrderNumber = TestUtil.getRandomObject(projects);
        originalProject = projects.get(projectOrderNumber);
        newProject.setId(originalProject.getId());
    }

    @Test(priority = 2, description = "Chỉnh sửa thông tin Project")
    public void editProjectTest() throws URISyntaxException {
        MediaCaseTemplate mediaCaseTemplate = new MediaCaseTemplate(adminUser);

        //Get Image Link
        Media cccdFile = mediaHandler.findByName(MediaEnum.CCCD_FILE);
        String newImage = mediaCaseTemplate.upload(cccdFile , FileType.AVATAR)
                .jsonPath()
                .getString("data");
        newProject.setImage(newImage);
        //Check Edit Project
        BaseChecker.assertNotNullResponse(projectCaseTemplate.editProject(newProject));
    }

    @Test(priority = 3, description = "Kiểm tra thông tin Project")
    public void checkEditedProject(){
        Response response = projectCaseTemplate.getAllProject();
        BaseChecker.assertNotNullResponse(response);
        List<Project> projects = Arrays.asList(response.body().jsonPath().getObject("$",Project[].class));

        // Compare edited project field with new project field
        assertEquals(projects.get(projectOrderNumber).getName(), newProject.getName());
        assertEquals(projects.get(projectOrderNumber).getImage(), newProject.getImage());
    }
    @Test(priority = 4, description = "Trả thông tin Project lại như ban đầu")
    public void revertOriginalData() {
        Response response = projectCaseTemplate.editProject(originalProject);
        BaseChecker.assertNotNullResponse(response);
                response.then()
                .assertThat()
                .body("data.name", equalTo(originalProject.getName()))
                .body("data.image", equalTo(originalProject.getImage()))
                .log().ifValidationFails();
    }
}
