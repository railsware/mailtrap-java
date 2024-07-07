package io.mailtrap.config;

import io.mailtrap.exception.BaseMailtrapException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * Sending configuration class for Mailtrap SDK.
 */
@Getter
public class SendingConfig {
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

    // Private constructor to enforce the use of the builder
    private SendingConfig(Builder builder) {
        if (builder.sandbox && builder.bulk) {
            throw new BaseMailtrapException("Bulk mode is not applicable for Sandbox API");
        }
        if(builder.sandbox && Objects.isNull(builder.inboxId)) {
            throw new BaseMailtrapException("Sandbox API requires inbox ID");
        }
        this.sandbox = builder.sandbox;
        this.bulk = builder.bulk;
        this.inboxId = builder.inboxId;
    }

    @ToString
    @NoArgsConstructor
    public static class Builder {
        private boolean sandbox;
        private boolean bulk;
        private Integer inboxId;

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

        public SendingConfig build() {
            return new SendingConfig(this);
        }
    }

    // Method to return a builder pre-populated with the current values
    public Builder toBuilder() {
        return new Builder()
                .sandbox(this.sandbox)
                .bulk(this.bulk)
                .inboxId(this.inboxId);
    }

}
