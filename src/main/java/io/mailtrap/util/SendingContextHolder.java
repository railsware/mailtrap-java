package io.mailtrap.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Utility class which holds sending context (testing/bulk/production env, inbox id) to make it possible to perform
 * send directly from {@link io.mailtrap.client.MailtrapClient}
 */
@Getter
@Builder
@Setter
public class SendingContextHolder {
    private boolean sandbox;
    private Integer inboxId;
    private boolean bulk;
}
