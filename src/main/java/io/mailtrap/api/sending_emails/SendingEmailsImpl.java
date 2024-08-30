package io.mailtrap.api.sending_emails;

import io.mailtrap.Constants;
import io.mailtrap.CustomValidator;
import io.mailtrap.api.api_resource.SendApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.emails.MailtrapMail;
import io.mailtrap.model.response.emails.SendResponse;

/**
 * Implementation of the {@link SendingEmails} interface for sending emails in the production environment.
 */
public class SendingEmailsImpl extends SendApiResource implements SendingEmails {

    public SendingEmailsImpl(MailtrapConfig config, CustomValidator customValidator) {
        super(config, customValidator);
        this.apiHost = Constants.EMAIL_SENDING_SEND_HOST;
    }

    @Override
    public SendResponse send(MailtrapMail mail) {
        validateRequestBodyOrThrowException(mail);
        RequestData requestData = new RequestData();
        if (mail.getHeaders() != null) {
            requestData.setHeaders(mail.getHeaders());
        }
        return httpClient.post(apiHost + "/api/send", mail, requestData, SendResponse.class);
    }

}
