package io.mailtrap.client.layers;

import io.mailtrap.api.abstractions.EmailTestingApi;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Represents an API for Mailtrap Testing functionality
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapEmailTestingApiLayer {
    private final EmailTestingApi emails;
}
