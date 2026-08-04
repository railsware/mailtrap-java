package io.mailtrap.examples.testing;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.projects.ProjectRequest;

public class ProjectsExample {

    private static final String TOKEN = System.getenv("MAILTRAP_API_KEY");
    private static final long ACCOUNT_ID = Long.parseLong(System.getenv("MAILTRAP_ACCOUNT_ID"));

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final var testingClient = MailtrapClientFactory.createMailtrapClient(config).testingApi();

        final var projects = testingClient.projects().getProjects(ACCOUNT_ID);

        if (!projects.isEmpty()) {
            long firstProjectId = projects.get(0).getId();

            final var updatedProject =
                    testingClient.projects().updateProject(ACCOUNT_ID, firstProjectId, new ProjectRequest(new ProjectRequest.ProjectData("mock project")));
            System.out.println(updatedProject);

            final var deletedProject = testingClient.projects().deleteProject(ACCOUNT_ID, firstProjectId);
            System.out.println(deletedProject);
        }
    }
}
