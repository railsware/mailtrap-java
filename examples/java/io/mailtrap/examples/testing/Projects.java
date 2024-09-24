package io.mailtrap.examples.testing;

import io.mailtrap.client.api.MailtrapEmailTestingApi;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.projects.CreateUpdateProjectRequest;
import io.mailtrap.model.response.projects.DeleteProjectResponse;
import io.mailtrap.model.response.projects.ProjectsResponse;

import java.util.List;

public class Projects {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long ACCOUNT_ID = 1L;

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapEmailTestingApi testingClient = MailtrapClientFactory.createMailtrapClient(config).testingApi();

        List<ProjectsResponse> projects = testingClient.projects().getProjects(ACCOUNT_ID);

        if (!projects.isEmpty()) {
            long firstProjectId = projects.get(0).getId();

            ProjectsResponse updatedProject =
                    testingClient.projects().updateProject(ACCOUNT_ID, firstProjectId, new CreateUpdateProjectRequest(new CreateUpdateProjectRequest.ProjectData("mock project")));
            System.out.println(updatedProject);

            DeleteProjectResponse deletedProject = testingClient.projects().deleteProject(ACCOUNT_ID, firstProjectId);
            System.out.println(deletedProject);
        }
    }
}
