package io.mailtrap.client.layers.wrapper;

import io.mailtrap.api.abstractions.BulkSendingApi;
import io.mailtrap.api.abstractions.EmailSendingApi;
import io.mailtrap.api.abstractions.EmailTestingApi;
import io.mailtrap.config.SendingConfig;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailtrapSendingWrapper {

    private final EmailSendingApi sendingApi;
    private final EmailTestingApi testingApi;
    private final BulkSendingApi bulkSendingApi;

    public SendResponse send(MailtrapMail mailtrapMail, SendingConfig sendingConfig) {
        if (sendingConfig.isBulk()) {
            return bulkSendingApi.send(mailtrapMail);
        } else if (sendingConfig.isSandbox()) {
            return testingApi.send(mailtrapMail, sendingConfig.getInboxId());
        } else {
            return sendingApi.send(mailtrapMail);
        }
    }
}
