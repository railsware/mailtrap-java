package io.mailtrap.api.contacts;

import io.mailtrap.Constants;
import io.mailtrap.api.api_resource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.contacts.CreateContactRequest;
import io.mailtrap.model.response.contacts.CreateContactResponse;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;

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
                String.format(apiHost + "/api/accounts/%s/contacts/%s", accountId, URLEncoder.encode(idOrEmail, Charset.defaultCharset())),
                new RequestData(),
                Void.class
        );
    }
}
