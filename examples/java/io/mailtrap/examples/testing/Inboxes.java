package io.mailtrap.examples.testing;

import io.mailtrap.client.api.MailtrapEmailTestingApi;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.inboxes.CreateInboxRequest;
import io.mailtrap.model.request.inboxes.UpdateInboxRequest;
import io.mailtrap.model.response.inboxes.InboxResponse;
import io.mailtrap.model.response.projects.ProjectsResponse;

import java.util.List;

public class Inboxes {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long ACCOUNT_ID = 1L;

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapEmailTestingApi testingClient = MailtrapClientFactory.createMailtrapClient(config).testingApi();

        List<ProjectsResponse> projects = testingClient.projects().getProjects(ACCOUNT_ID);

        if (!projects.isEmpty()) {
            ProjectsResponse firstProject = projects.get(0);

            InboxResponse createdInbox = testingClient.inboxes().createInbox(ACCOUNT_ID, firstProject.getId(), new CreateInboxRequest(new CreateInboxRequest.InboxCreateData("test-inbox")));
            System.out.println(createdInbox);

            List<InboxResponse> inboxes = testingClient.inboxes().getInboxes(ACCOUNT_ID);

            if (!inboxes.isEmpty()) {
                long firstInboxId = inboxes.get(0).getId();

                InboxResponse inboxAttributes = testingClient.inboxes().getInboxAttributes(ACCOUNT_ID, firstInboxId);
                System.out.println(inboxAttributes);

                InboxResponse inboxUpdate = testingClient.inboxes().updateInbox(ACCOUNT_ID, firstInboxId, new UpdateInboxRequest(new UpdateInboxRequest.InboxUpdateData("updated-test-inbox", "mock")));
                System.out.println(inboxUpdate);

                InboxResponse markAsRead = testingClient.inboxes().markAsRead(ACCOUNT_ID, firstInboxId);
                System.out.println(markAsRead);

                InboxResponse cleanInbox = testingClient.inboxes().cleanInbox(ACCOUNT_ID, firstInboxId);
                System.out.println(cleanInbox);

                InboxResponse resetCredentials = testingClient.inboxes().resetCredentials(ACCOUNT_ID, firstInboxId);
                System.out.println(resetCredentials);

                InboxResponse enableEmailAddress = testingClient.inboxes().enableEmailAddress(ACCOUNT_ID, firstInboxId);
                System.out.println(enableEmailAddress);

                InboxResponse resetEmailAddresses = testingClient.inboxes().resetEmailAddresses(ACCOUNT_ID, firstInboxId);
                System.out.println(resetEmailAddresses);
            }
        }
    }
}
