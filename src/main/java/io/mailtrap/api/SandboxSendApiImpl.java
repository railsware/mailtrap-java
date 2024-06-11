package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.CustomValidator;
import io.mailtrap.api.abstractions.SandboxSendApi;
import io.mailtrap.api.abstractions.SendApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;

/**
 * Implementation of the {@link SandboxSendApi} interface for sending emails in the sandbox environment.
 */
public class SandboxSendApiImpl extends SendApiResource implements SandboxSendApi {

    public SandboxSendApiImpl(MailtrapConfig config, CustomValidator customValidator) {
        super(config, customValidator);
        this.apiBaseUrl = Constants.SANDBOX_SEND_URL;
    }

    @Override
    public SendResponse send(MailtrapMail mail, int inboxId) {
        validateRequestBodyOrThrowException(mail);
        RequestData requestData = new RequestData();
        if (mail.getHeaders() != null) {
            requestData.setHeaders(mail.getHeaders());
        }
        return httpClient.post(String.format(apiBaseUrl + "/api/send/%d", inboxId), mail, requestData, SendResponse.class);
    }
}
