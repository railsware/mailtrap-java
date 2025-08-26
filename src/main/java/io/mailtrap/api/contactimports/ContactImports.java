package io.mailtrap.api.contactimports;

import io.mailtrap.model.request.contactimports.ImportContactsRequest;
import io.mailtrap.model.response.contactimports.ImportContactsResponse;

public interface ContactImports {

  /**
   * Import contacts in bulk with support for custom fields and list management.
   * Existing contacts with matching email addresses will be updated automatically.
   * Up to 50,000 contacts per request
   *
   * @param accountId unique account ID
   * @param request   request body
   * @return contact data
   */
  ImportContactsResponse importContacts(long accountId, ImportContactsRequest request);

  /**
   * Get Contact Import
   *
   * @param accountId unique account ID
   * @param contactId unique Contact Import ID
   * @return contact data
   */
  ImportContactsResponse getContactImport(long accountId, long contactId);
}
