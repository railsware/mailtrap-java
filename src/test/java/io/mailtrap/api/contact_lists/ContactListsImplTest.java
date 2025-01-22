package io.mailtrap.api.contact_lists;

import io.mailtrap.Constants;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.response.contact_lists.ContactListsResponse;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactListsImplTest extends BaseTest {
    private ContactLists api;

    @BeforeEach
    public void init() {
        TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/contacts/lists",
                        "GET", null, "api/contact_lists/contactLists.json")
        ));

        MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).contactsApi().contactLists();
    }

    @Test
    void test_findAll() {
        List<ContactListsResponse> contacts = api.findAll(accountId);

        assertFalse(contacts.isEmpty());
        assertEquals(2, contacts.size());
    }
}
