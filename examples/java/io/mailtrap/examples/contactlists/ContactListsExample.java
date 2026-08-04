package io.mailtrap.examples.contactlists;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.contactlists.ContactListRequest;
import io.mailtrap.model.request.contactlists.ListContactListsQueryParams;

public class ContactListsExample {

    private static final String TOKEN = System.getenv("MAILTRAP_API_KEY");
    private static final long ACCOUNT_ID = Long.parseLong(System.getenv("MAILTRAP_ACCOUNT_ID"));
    private static final String NAME_FOR_CREATE = "Clients";
    private static final String NAME_FOR_UPDATE = "Customers";
    private static final String SEARCH_QUERY = "Cust";

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
            .token(TOKEN)
            .build();

        final var client = MailtrapClientFactory.createMailtrapClient(config);

        final var created = client.contactsApi().contactLists().createContactList(ACCOUNT_ID, new ContactListRequest(NAME_FOR_CREATE));
        System.out.println(created);

        final var updated = client.contactsApi().contactLists().updateContactList(ACCOUNT_ID, created.getId(), new ContactListRequest(NAME_FOR_UPDATE));
        System.out.println(updated);

        final var byId = client.contactsApi().contactLists().getContactList(ACCOUNT_ID, updated.getId());
        System.out.println(byId);

        final var contactLists = client.contactsApi().contactLists().findAll(ACCOUNT_ID);
        System.out.println(contactLists);

        // Filter contact lists by name (case-insensitive prefix match)
        final var searchParams = new ListContactListsQueryParams(SEARCH_QUERY);
        final var matchingContactLists = client.contactsApi().contactLists().findAll(ACCOUNT_ID, searchParams);
        System.out.println(matchingContactLists);

        client.contactsApi().contactLists().deleteContactList(ACCOUNT_ID, byId.getId());
    }
}
