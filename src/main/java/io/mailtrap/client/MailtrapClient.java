package io.mailtrap.client;

import io.mailtrap.client.layers.MailtrapBulkEmailSendingApiLayer;
import io.mailtrap.client.layers.MailtrapEmailSendingApiLayer;
import io.mailtrap.client.layers.MailtrapEmailTestingApiLayer;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.model.request.MailtrapMail;
import io.mailtrap.model.response.SendResponse;
import io.mailtrap.util.SendingContextHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Client for interacting with Mailtrap APIs.
 * Emails might be sent using different dedicated APIs OR based on the configuration
 * provided by the {@link SendingContextHolder} and {@link MailtrapConfig} and {@link #send(MailtrapMail)}
 */
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapClient {

    /**
     * API for Mailtrap.io Sending functionality
     */
    @Getter
    private final MailtrapEmailSendingApiLayer sendingApi;

    /**
     * API for Mailtrap.io Testing functionality
     */
    @Getter
    private final MailtrapEmailTestingApiLayer testingApi;

    /**
     * API for Mailtrap.io Bulk Sending functionality
     */
    @Getter
    private final MailtrapBulkEmailSendingApiLayer bulkSendingApi;

    /**
     * Utility class which holds sending context (testing/bulk/production env, inbox id) to make it possible to perform
     * send directly from MailtrapClient
     */
    private final SendingContextHolder sendingContextHolder;

    /**
     * Sends an email based on the specified sending configuration.
     *
     * @param mailtrapMail the email to send
     * @return the response from the sending operation
     */
    public SendResponse send(MailtrapMail mailtrapMail) {
        if (sendingContextHolder.isBulk()) {
            return bulkSendingApi.emails().send(mailtrapMail);
        } else if (sendingContextHolder.isSandbox()) {
            return testingApi.emails().send(mailtrapMail, sendingContextHolder.getInboxId());
        } else {
            return sendingApi.emails().send(mailtrapMail);
        }
    }

    /**
     * Initializes the context for bulk sending.
     */
    public void initBulkSend() {
        this.sendingContextHolder.setBulk(true);
        this.sendingContextHolder.setInboxId(null);
        this.sendingContextHolder.setSandbox(false);
    }

    /**
     * Initializes the context for testing sending.
     *
     * @param inboxId the inbox ID to use for testing
     */
    public void initTestingSend(Integer inboxId) {
        this.sendingContextHolder.setInboxId(inboxId);
        this.sendingContextHolder.setSandbox(true);
        this.sendingContextHolder.setBulk(false);
    }

    /**
     * Initializes the context for production sending.
     */
    public void initProductionSend() {
        this.sendingContextHolder.setInboxId(null);
        this.sendingContextHolder.setSandbox(false);
        this.sendingContextHolder.setBulk(false);
    }
}
