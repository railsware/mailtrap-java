package io.mailtrap.api.contacts;

import io.mailtrap.model.request.contacts.CreateContactRequest;
import io.mailtrap.model.response.contacts.CreateContactResponse;

public interface Contacts {

    /**
     * Create a new contact
     *
     * @param accountId unique account ID
     * @param request   required contact data
     * @return created contact
     */
    CreateContactResponse createContact(long accountId, CreateContactRequest request);

    /**
     * Delete contact using id or email (URL encoded)
     *
     * @param accountId unique account ID
     * @param idOrEmail contact ID or Email
     */
    void deleteContact(long accountId, String idOrEmail);

}
