package io.mailtrap.client;

import io.mailtrap.client.layers.wrapper.MailtrapSendingWrapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Client for interacting with Mailtrap APIs.
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapClient {

    /**
     * Wrapper for all three send operations - sandbox, send and bulk send
     */
    private final MailtrapSendingWrapper emails;

}
