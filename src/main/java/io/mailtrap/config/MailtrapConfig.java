package io.mailtrap.config;

import io.mailtrap.http.CustomHttpClient;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;

/**
 * Configuration class for Mailtrap SDK.
 */
@Getter
@Builder(toBuilder = true)
public class MailtrapConfig {

    /**
     * The duration for connection timeout.
     */
    private final Duration connectionTimeout;

    /**
     * The authentication token used for accessing the Mailtrap API.
     */
    private final String token;

    /**
     * The custom HTTP client.
     */
    private final CustomHttpClient httpClient;

    /**
     * The sending config
     */
    private final SendingConfig sendingConfig;

}
