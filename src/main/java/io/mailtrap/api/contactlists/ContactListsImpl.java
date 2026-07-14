package io.mailtrap.api.contactlists;

import io.mailtrap.Constants;
import io.mailtrap.api.apiresource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.contactlists.ContactListRequest;
import io.mailtrap.model.request.contactlists.ListContactListsQueryParams;
import io.mailtrap.model.response.contactlists.ContactListResponse;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

import static io.mailtrap.http.RequestData.entry;

public class ContactListsImpl extends ApiResource implements ContactLists {

    public ContactListsImpl(final MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public List<ContactListResponse> findAll(final long accountId) {
        return findAll(accountId, ListContactListsQueryParams.empty());
    }

    @Override
    public List<ContactListResponse> findAll(final long accountId, @NonNull final ListContactListsQueryParams queryParams) {
        final var params = RequestData.buildQueryParams(
            entry("search", Optional.ofNullable(queryParams.getSearch())));

        return httpClient.getList(
            String.format(apiHost + "/api/accounts/%d/contacts/lists", accountId),
            new RequestData(params),
            ContactListResponse.class
        );
    }

    @Override
    public ContactListResponse createContactList(final long accountId, final ContactListRequest request) {
        return httpClient.post(
            String.format(apiHost + "/api/accounts/%d/contacts/lists", accountId),
            request,
            new RequestData(),
            ContactListResponse.class
        );
    }

    @Override
    public ContactListResponse getContactList(final long accountId, final long contactListId) {
        return httpClient.get(
            String.format(apiHost + "/api/accounts/%d/contacts/lists/%d", accountId, contactListId),
            new RequestData(),
            ContactListResponse.class
        );
    }

    @Override
    public ContactListResponse updateContactList(final long accountId, final long contactListId, final ContactListRequest request) {
        return httpClient.patch(
            String.format(apiHost + "/api/accounts/%d/contacts/lists/%d", accountId, contactListId),
            request,
            new RequestData(),
            ContactListResponse.class
        );
    }

    @Override
    public void deleteContactList(final long accountId, final long contactListId) {
        httpClient.delete(
            String.format(apiHost + "/api/accounts/%d/contacts/lists/%d", accountId, contactListId),
            new RequestData(),
            Void.class
        );
    }
}
