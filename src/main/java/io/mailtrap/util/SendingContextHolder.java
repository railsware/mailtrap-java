package io.mailtrap.util;

import io.mailtrap.client.MailtrapClient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Utility class which holds sending context (which API to use: Email Sending API, Bulk Sending API or
 * Email Testing API, inbox id for Email Testing API) to make it possible to perform send directly from {@link MailtrapClient}
 */
@Getter
@Builder
@Setter
public class SendingContextHolder {
    private boolean sandbox;
    private Integer inboxId;
    private boolean bulk;
}
