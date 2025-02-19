package io.mailtrap.api.contactlists;

import io.mailtrap.model.response.contactlists.ContactListResponse;

import java.util.List;

public interface ContactLists {

    /**
     * Get all contact lists existing in account
     *
     * @param accountId unique account ID
     * @return contacts list
     */
    List<ContactListResponse> findAll(long accountId);
}
