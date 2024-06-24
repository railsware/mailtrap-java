package io.mailtrap.client;

import io.mailtrap.client.layers.MailtrapBulkEmailSendingApiLayer;
import io.mailtrap.client.layers.MailtrapEmailSendingApiLayer;
import io.mailtrap.client.layers.MailtrapEmailTestingApiLayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Client for interacting with Mailtrap APIs.
 */
@Getter
@RequiredArgsConstructor
public class MailtrapClient {

    /**
     * API for Mailtrap.io Sending functionality
     */
    private final MailtrapEmailSendingApiLayer sendingApi;

    /**
     * API for Mailtrap.io Testing functionality
     */
    private final MailtrapEmailTestingApiLayer testingApi;

    /**
     * API for Mailtrap.io Bulk Sending functionality
     */
    private final MailtrapBulkEmailSendingApiLayer bulkSendingApi;
}
