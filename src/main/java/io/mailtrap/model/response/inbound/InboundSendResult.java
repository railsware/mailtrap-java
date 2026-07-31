package io.mailtrap.model.response.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Result of a reply, reply-all, or forward (sends a real email).
 */
@Data
public class InboundSendResult {

    @JsonProperty("message_ids")
    private List<String> messageIds;
}
