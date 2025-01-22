package io.mailtrap.examples.contacts;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.contacts.Contact;
import io.mailtrap.model.request.contacts.CreateContactRequest;
import io.mailtrap.model.response.contacts.CreateContactResponse;

import java.util.List;
import java.util.Map;

public class Contacts {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long ACCOUNT_ID = 1L;
    private static final long ID_1 = 1L;
    private static final long ID_2 = 2L;
    private static final String EMAIL = "contact_email@email.com";

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        CreateContactRequest request = new CreateContactRequest(new Contact(EMAIL, Map.of("first_name", "Nick"), List.of(ID_1, ID_2)));

        CreateContactResponse response = client.contactsApi().contacts().createContact(ACCOUNT_ID, request);

        System.out.println(response);

        client.contactsApi().contacts().createContact(ACCOUNT_ID, email);
    }
}
