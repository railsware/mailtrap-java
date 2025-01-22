package io.mailtrap.api.contact_lists;

import io.mailtrap.Constants;
import io.mailtrap.api.api_resource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.response.contact_lists.ContactListsResponse;

import java.util.List;

public class ContactListsImpl extends ApiResource implements ContactLists {

    public ContactListsImpl(MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public List<ContactListsResponse> findAll(long accountId) {
        return httpClient.getList(
                String.format(apiHost + "/api/accounts/%s/contacts/lists", accountId),
                new RequestData(),
                ContactListsResponse.class
        );
    }
}
