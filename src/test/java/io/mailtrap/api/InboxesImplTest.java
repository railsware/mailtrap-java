package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.Inboxes;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.CreateInboxRequest;
import io.mailtrap.model.request.UpdateInboxRequest;
import io.mailtrap.model.request.UpdateInboxRequest.InboxUpdateData;
import io.mailtrap.model.response.InboxResponse;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.mailtrap.model.request.CreateInboxRequest.*;
import static org.junit.jupiter.api.Assertions.*;

class InboxesImplTest extends BaseTest {

    private Inboxes api;

    @BeforeEach
    public void init() {
        TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/projects/" + projectId + "/inboxes",
                        "POST", "api/inbox/createInboxRequest.json", "api/inbox/inboxResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId,
                        "GET", null, "api/inbox/inboxResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId,
                        "DELETE", null, "api/inbox/inboxResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId,
                        "PATCH", "api/inbox/updateInboxRequest.json", "api/inbox/updateInboxResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId + "/clean",
                        "PATCH", null, "api/inbox/inboxResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId + "/all_read",
                        "PATCH", null, "api/inbox/inboxResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId + "/reset_credentials",
                        "PATCH", null, "api/inbox/inboxResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId + "/toggle_email_username",
                        "PATCH", null, "api/inbox/inboxResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/inboxes/" + inboxId + "/reset_email_username",
                        "PATCH", null, "api/inbox/inboxResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/inboxes",
                        "GET", null, "api/inbox/listInboxResponse.json")
        ));

        MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).testingApi().inboxes();
    }

    @Test
    void test_createInbox() {
        InboxResponse createdInbox = api.createInbox(accountId, projectId, new CreateInboxRequest(new InboxCreateData("Test inbox")));

        assertNotNull(createdInbox);
        assertEquals(createdInbox.getProjectId(), projectId);
        assertEquals(createdInbox.getName(), "Test inbox");
    }

    @Test
    void test_getInboxAttributes() {
        InboxResponse inboxAttributes = api.getInboxAttributes(accountId, inboxId);

        assertNotNull(inboxAttributes);
        assertTrue(inboxAttributes.getPermissions().isCanRead());
        assertEquals(inboxAttributes.getProjectId(), projectId);
        assertEquals(inboxAttributes.getName(), "Test inbox");
    }

    @Test
    void test_deleteInbox() {
        InboxResponse deletedInbox = api.deleteInbox(accountId, inboxId);

        assertNotNull(deletedInbox);
        assertTrue(deletedInbox.getPermissions().isCanRead());
        assertEquals(deletedInbox.getId(), inboxId);
        assertEquals(deletedInbox.getName(), "Test inbox");
    }

    @Test
    void test_updateInbox() {
        InboxResponse updatedInbox = api.updateInbox(accountId, inboxId, new UpdateInboxRequest(new InboxUpdateData("Updated Inbox Name", "updated-email-username")));

        assertNotNull(updatedInbox);
        assertTrue(updatedInbox.getPermissions().isCanRead());
        assertEquals(updatedInbox.getId(), inboxId);
        assertEquals(updatedInbox.getName(), "Updated Inbox Name");
        assertEquals(updatedInbox.getEmailUsername(), "updated-email-username");
    }

    @Test
    void test_cleanInbox() {
        InboxResponse cleanedInbox = api.cleanInbox(accountId, inboxId);

        assertNotNull(cleanedInbox);
        assertTrue(cleanedInbox.getPermissions().isCanRead());
        assertEquals(cleanedInbox.getId(), inboxId);
        assertEquals(cleanedInbox.getName(), "Test inbox");
    }

    @Test
    void test_markAsRead() {
        InboxResponse markedAsRead = api.markAsRead(accountId, inboxId);

        assertNotNull(markedAsRead);
        assertTrue(markedAsRead.getPermissions().isCanRead());
        assertEquals(markedAsRead.getId(), inboxId);
        assertEquals(markedAsRead.getName(), "Test inbox");
    }

    @Test
    void test_resetCredentials() {
        InboxResponse resetCredentials = api.resetCredentials(accountId, inboxId);

        assertNotNull(resetCredentials);
        assertTrue(resetCredentials.getPermissions().isCanRead());
        assertEquals(resetCredentials.getId(), inboxId);
        assertEquals(resetCredentials.getName(), "Test inbox");
    }

    @Test
    void test_enableEmailAddress() {
        InboxResponse enableEmailAddress = api.enableEmailAddress(accountId, inboxId);

        assertNotNull(enableEmailAddress);
        assertTrue(enableEmailAddress.getPermissions().isCanRead());
        assertEquals(enableEmailAddress.getId(), inboxId);
        assertEquals(enableEmailAddress.getName(), "Test inbox");
    }

    @Test
    void test_resetEmailAddresses() {
        InboxResponse resetEmailAddresses = api.resetEmailAddresses(accountId, inboxId);

        assertNotNull(resetEmailAddresses);
        assertTrue(resetEmailAddresses.getPermissions().isCanRead());
        assertEquals(resetEmailAddresses.getId(), inboxId);
        assertEquals(resetEmailAddresses.getName(), "Test inbox");
    }

    @Test
    void test_getInboxes() {
        List<InboxResponse> inboxes = api.getInboxes(accountId);

        assertNotNull(inboxes);
        assertEquals(1, inboxes.size());
        assertTrue(inboxes.get(0).getPermissions().isCanRead());
        assertEquals(inboxes.get(0).getId(), inboxId);
        assertEquals(inboxes.get(0).getName(), "Test inbox");
    }

}
