package io.mailtrap.examples.contact_lists;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.response.contact_lists.ContactListsResponse;

import java.util.List;

public class ContactLists {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long ACCOUNT_ID = 1L;

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        List<ContactListsResponse> contacts = client.contactsApi().contactLists().findAll(ACCOUNT_ID);

        System.out.println(contacts);
    }
}
