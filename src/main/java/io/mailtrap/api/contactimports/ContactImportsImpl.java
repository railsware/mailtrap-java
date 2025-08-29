package io.mailtrap.api.contactimports;

import io.mailtrap.Constants;
import io.mailtrap.CustomValidator;
import io.mailtrap.api.apiresource.ApiResourceWithValidation;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.contactimports.ImportContactsRequest;
import io.mailtrap.model.response.contactimports.ContactsImportResponse;
import io.mailtrap.model.response.contactimports.CreateContactsImportResponse;

public class ContactImportsImpl extends ApiResourceWithValidation implements ContactImports {

  public ContactImportsImpl(final MailtrapConfig config, final CustomValidator validator) {
    super(config, validator);
    this.apiHost = Constants.GENERAL_HOST;
  }

  @Override
  public CreateContactsImportResponse importContacts(long accountId, ImportContactsRequest request) {

    validateRequestBodyAndThrowException(request);

    return httpClient.post(
        String.format(apiHost + "/api/accounts/%s/contacts/imports", accountId),
        request,
        new RequestData(),
        CreateContactsImportResponse.class
    );
  }

  @Override
  public ContactsImportResponse getContactImport(long accountId, long importId) {
    return httpClient.get(
        String.format(apiHost + "/api/accounts/%s/contacts/imports/%s", accountId, importId),
        new RequestData(),
        ContactsImportResponse.class
    );
  }
}
