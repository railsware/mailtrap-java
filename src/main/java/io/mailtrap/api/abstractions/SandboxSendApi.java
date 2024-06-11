package io.mailtrap.api.abstractions;

import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;

/**
 * Interface representing the sandbox environment API for sending emails.
 */
public interface SandboxSendApi {

    /**
     * Sends an email
     *
     * @param mail The email message to be sent.
     * @return A response indicating the result of the send operation.
     */
    SendResponse send(MailtrapMail mail, int inboxId);
}
