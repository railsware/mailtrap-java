package io.mailtrap.api.contact_lists;

import io.mailtrap.model.response.contact_lists.ContactListsResponse;

import java.util.List;

public interface ContactLists {

    /**
     * Get all contact lists existing in account
     *
     * @param accountId unique account ID
     * @return contacts list
     */
    List<ContactListsResponse> findAll(long accountId);
}
