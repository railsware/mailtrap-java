package io.mailtrap.api.contacts;

import io.mailtrap.Constants;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.contacts.Contact;
import io.mailtrap.model.request.contacts.CreateContactRequest;
import io.mailtrap.model.response.contacts.ContactStatus;
import io.mailtrap.model.response.contacts.CreateContactResponse;
import io.mailtrap.testutils.BaseTest;
import io.mailtrap.testutils.DataMock;
import io.mailtrap.testutils.TestHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ContactsImplTest extends BaseTest {
    private Contacts api;

    @BeforeEach
    public void init() {
        TestHttpClient httpClient = new TestHttpClient(List.of(
                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/contacts",
                        "POST", "api/contacts/createContactRequest.json", "api/contacts/createContactResponse.json"),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/contacts/" + emailEncoded,
                        "DELETE", null, null),

                DataMock.build(Constants.GENERAL_HOST + "/api/accounts/" + accountId + "/contacts/" + contactUUIDEncoded,
                        "DELETE", null, null)
        ));

        MailtrapConfig testConfig = new MailtrapConfig.Builder()
                .httpClient(httpClient)
                .token("dummy_token")
                .build();

        api = MailtrapClientFactory.createMailtrapClient(testConfig).contactsApi().contacts();
    }

    @Test
    void test_createContact() {
        CreateContactRequest request = new CreateContactRequest(new Contact(email, Map.of("first_name", "Nick"), List.of(1L, 2L)));

        CreateContactResponse response = api.createContact(accountId, request);

        assertNotNull(response);
        assertEquals(ContactStatus.SUBSCRIBED, response.getStatus());
    }

    @Test
    void test_deleteContact_byEmail() {
        api.deleteContact(accountId, email);
    }

    @Test
    void test_deleteContact_byId() {
        api.deleteContact(accountId, contactUUID);
    }
}
