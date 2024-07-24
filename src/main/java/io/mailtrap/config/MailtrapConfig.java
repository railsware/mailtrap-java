package io.mailtrap.config;

import io.mailtrap.exception.BaseMailtrapException;
import io.mailtrap.http.CustomHttpClient;
import lombok.Getter;

import java.time.Duration;
import java.util.Objects;

/**
 * Configuration class for Mailtrap SDK.
 */
@Getter
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
     * Indicates whether to use Sandbox or Send API
     */
    private final boolean sandbox;

    /**
     * Indicates whether to use Bulk API
     */
    private final boolean bulk;

    /**
     * Inbox ID. Should be used alongside with {@link #sandbox}, as Sandbox API requires inbox ID
     */
    private final Integer inboxId;

    private MailtrapConfig(Builder builder) {
        if (builder.sandbox && builder.bulk) {
            throw new BaseMailtrapException("Bulk mode is not applicable for Sandbox API");
        }
        if (builder.sandbox && Objects.isNull(builder.inboxId)) {
            throw new BaseMailtrapException("Sandbox API requires inbox ID");
        }

        this.connectionTimeout = builder.connectionTimeout;
        this.token = builder.token;
        this.httpClient = builder.httpClient;
        this.sandbox = builder.sandbox;
        this.bulk = builder.bulk;
        this.inboxId = builder.inboxId;
    }

    public static class Builder {
        private Duration connectionTimeout;
        private String token;
        private CustomHttpClient httpClient;
        private boolean sandbox;
        private boolean bulk;
        private Integer inboxId;

        public Builder connectionTimeout(Duration connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder httpClient(CustomHttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public Builder sandbox(boolean sandbox) {
            this.sandbox = sandbox;
            return this;
        }

        public Builder bulk(boolean bulk) {
            this.bulk = bulk;
            return this;
        }

        public Builder inboxId(Integer inboxId) {
            this.inboxId = inboxId;
            return this;
        }

        public MailtrapConfig build() {
            return new MailtrapConfig(this);
        }
    }
}
