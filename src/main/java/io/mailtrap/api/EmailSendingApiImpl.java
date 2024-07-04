package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.CustomValidator;
import io.mailtrap.api.abstractions.EmailSendingApi;
import io.mailtrap.api.abstractions.classes.SendApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;

/**
 * Implementation of the {@link EmailSendingApi} interface for sending emails in the production environment.
 */
public class EmailSendingApiImpl extends SendApiResource implements EmailSendingApi {

    public EmailSendingApiImpl(MailtrapConfig config, CustomValidator customValidator) {
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
