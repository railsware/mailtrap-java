package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.CustomValidator;
import io.mailtrap.api.abstractions.ProductionSendApi;
import io.mailtrap.api.abstractions.SendApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;

/**
 * Implementation of the {@link ProductionSendApi} interface for sending emails in the production environment.
 */
public class ProductionSendApiImpl extends SendApiResource implements ProductionSendApi {

    public ProductionSendApiImpl(MailtrapConfig config, CustomValidator customValidator) {
        super(config, customValidator);
        this.apiBaseUrl = Constants.PRODUCTION_SEND_URL;
    }

    @Override
    public SendResponse send(MailtrapMail mail) {
        validateRequestBodyOrThrowException(mail);
        RequestData requestData = new RequestData();
        if (mail.getHeaders() != null) {
            requestData.setHeaders(mail.getHeaders());
        }
        return httpClient.post(apiBaseUrl + "/api/send", mail, requestData, SendResponse.class);
    }

}