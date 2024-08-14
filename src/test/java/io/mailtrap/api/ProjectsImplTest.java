package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.Projects;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.CreateUpdateProjectRequest;
import io.mailtrap.model.request.CreateUpdateProjectRequest.ProjectData;
import io.mailtrap.model.response.DeleteProjectResponse;
import io.mailtrap.model.response.ProjectsResponse;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProjectsImplTest extends BaseTest {

    private Projects api;

    @BeforeEach
    public void init() {
        TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/projects",
                        "POST", "api/projects/createProjectsRequest.json", "api/projects/projectResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/projects/" + projectId,
                        "GET", null, "api/projects/projectResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/projects/" + projectId,
                        "DELETE", null, "api/projects/deleteProjectResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/projects/" + projectId,
                        "PATCH", "api/projects/updateProjectRequest.json", "api/projects/updateProjectResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/projects",
                        "GET", null, "api/projects/listProjectsResponse.json")
        ));

        MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).testingApi().projects();
    }

    @Test
    void test_createProject() {
        ProjectsResponse createdProject = api.createProject(accountId, new CreateUpdateProjectRequest(new ProjectData("My New Project")));

        assertNotNull(createdProject);
        assertEquals(projectId, createdProject.getId());
        assertEquals("My New Project", createdProject.getName());
    }

    @Test
    void test_getProjects() {
        List<ProjectsResponse> projects = api.getProjects(accountId);

        assertEquals(1, projects.size());
        assertEquals(projectId, projects.get(0).getId());
        assertEquals("My New Project", projects.get(0).getName());
        assertEquals(0, projects.get(0).getInboxes().size());
    }

    @Test
    void test_getProject() {
        ProjectsResponse project = api.getProject(accountId, projectId);

        assertNotNull(project);
        assertEquals(projectId, project.getId());
        assertEquals("My New Project", project.getName());
        assertEquals(0, project.getInboxes().size());
    }

    @Test
    void test_updateProject() {
        ProjectsResponse updatedProject = api.updateProject(accountId, projectId, new CreateUpdateProjectRequest(new ProjectData("Updated Project Name")));

        assertNotNull(updatedProject);
        assertEquals(projectId, updatedProject.getId());
        assertEquals("Updated Project Name", updatedProject.getName());
        assertEquals(1, updatedProject.getInboxes().size());
    }

    @Test
    void test_deleteProject() {
        DeleteProjectResponse deletedProject = api.deleteProject(accountId, projectId);

        assertEquals(deletedProject.getId(), projectId);
    }
}
