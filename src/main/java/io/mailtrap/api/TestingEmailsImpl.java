package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.CustomValidator;
import io.mailtrap.api.abstractions.TestingEmails;
import io.mailtrap.api.abstractions.classes.SendApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;

/**
 * Implementation of the {@link TestingEmails} interface for sending emails in the sandbox environment.
 */
public class TestingEmailsImpl extends SendApiResource implements TestingEmails {

    public TestingEmailsImpl(MailtrapConfig config, CustomValidator customValidator) {
        super(config, customValidator);
        this.apiHost = Constants.EMAIL_TESTING_SEND_HOST;
    }

    @Override
    public SendResponse send(MailtrapMail mail, int inboxId) {
        validateRequestBodyOrThrowException(mail);
        RequestData requestData = new RequestData();
        if (mail.getHeaders() != null) {
            requestData.setHeaders(mail.getHeaders());
        }
        return httpClient.post(String.format(apiHost + "/api/send/%d", inboxId), mail, requestData, SendResponse.class);
    }
}
