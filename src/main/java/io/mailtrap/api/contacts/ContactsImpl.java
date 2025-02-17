package io.mailtrap.api.contacts;

import io.mailtrap.Constants;
import io.mailtrap.api.apiresource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.contacts.CreateContactRequest;
import io.mailtrap.model.request.contacts.UpdateContactRequest;
import io.mailtrap.model.response.contacts.CreateContactResponse;
import io.mailtrap.model.response.contacts.UpdateContactResponse;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class ContactsImpl extends ApiResource implements Contacts {

  public ContactsImpl(MailtrapConfig config) {
    super(config);
    this.apiHost = Constants.GENERAL_HOST;
  }

  @Override
  public CreateContactResponse createContact(long accountId, CreateContactRequest request) {
    return httpClient.post(
        String.format(apiHost + "/api/accounts/%s/contacts", accountId),
        request,
        new RequestData(),
        CreateContactResponse.class
    );
  }

  @Override
  public void deleteContact(long accountId, String idOrEmail) {
    httpClient.delete(
        String.format(apiHost + "/api/accounts/%s/contacts/%s", accountId,
            URLEncoder.encode(idOrEmail, Charset.defaultCharset())),
        new RequestData(),
        Void.class
    );
  }

  @Override
  public UpdateContactResponse updateContact(long accountId, String idOrEmail,
      UpdateContactRequest request) {
    return httpClient.patch(
        String.format(apiHost + "/api/accounts/%s/contacts/%s", accountId,
            URLEncoder.encode(idOrEmail, Charset.defaultCharset())),
        request,
        new RequestData(),
        UpdateContactResponse.class
    );
  }
}
