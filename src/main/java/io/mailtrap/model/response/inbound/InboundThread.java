package io.mailtrap.model.response.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * A conversation thread. {@code messages} is populated on get-by-id; list items
 * carry only the summary fields.
 */
@Data
public class InboundThread {

    private String id;

    private String subject;

    @JsonProperty("message_count")
    private Integer messageCount;

    private Integer size;

    @JsonProperty("first_message_at")
    private OffsetDateTime firstMessageAt;

    @JsonProperty("last_received_at")
    private OffsetDateTime lastReceivedAt;

    @JsonProperty("last_sent_at")
    private OffsetDateTime lastSentAt;

    @JsonProperty("last_activity_at")
    private OffsetDateTime lastActivityAt;

    @JsonProperty("last_message_id")
    private String lastMessageId;

    private List<String> senders;

    private List<String> recipients;

    private List<InboundAttachment> attachments;

    private List<InboundThreadMessage> messages;
}
