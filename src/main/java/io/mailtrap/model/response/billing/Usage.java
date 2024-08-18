package io.mailtrap.model.response.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Usage {

    @JsonProperty("sent_messages_count")
    private UsageCount sentMessagesCount;

    @JsonProperty("forwarded_messages_count")
    private UsageCount forwardedMessagesCount;
}
