package io.mailtrap.client;

import io.mailtrap.api.abstractions.ProductionSendApi;
import io.mailtrap.api.abstractions.SandboxSendApi;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Client for interacting with Mailtrap APIs.
 */
@Getter
@RequiredArgsConstructor
public class MailtrapClient {

    /**
     * The production environment API for sending emails.
     */
    private final ProductionSendApi productionSendApi;

    /**
     * The sandbox environment API for sending emails.
     */
    private final SandboxSendApi sandboxSendApi;
}