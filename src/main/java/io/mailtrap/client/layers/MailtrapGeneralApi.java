package io.mailtrap.client.layers;

import io.mailtrap.api.abstractions.AccountAccesses;
import io.mailtrap.api.abstractions.Permissions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Represents an API for Mailtrap General functionality
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapGeneralApi {
    private final AccountAccesses accountAccesses;
    private final Permissions permissions;
}
