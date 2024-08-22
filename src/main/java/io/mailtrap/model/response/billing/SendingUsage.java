package io.mailtrap.model.response.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendingUsage {

    @JsonProperty("sent_messages_count")
    private UsageCount sentMessagesCount;

}
