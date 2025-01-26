package io.mailtrap.examples.contacts;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.contacts.Contact;
import io.mailtrap.model.request.contacts.CreateContactRequest;

import java.util.List;
import java.util.Map;

public class Contacts {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long ACCOUNT_ID = 1L;
    private static final long LIST_1_ID = 1L;
    private static final long LIST_2_ID = 2L;
    private static final String EMAIL = "contact_email@email.com";

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final var client = MailtrapClientFactory.createMailtrapClient(config);

        var request = new CreateContactRequest(new Contact(EMAIL, Map.of("first_name", "Nick"), List.of(LIST_1_ID, LIST_2_ID)));

        var response = client.contactsApi().contacts().createContact(ACCOUNT_ID, request);

        System.out.println(response);

        client.contactsApi().contacts().deleteContact(ACCOUNT_ID, email);
    }
}
