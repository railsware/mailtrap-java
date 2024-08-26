package io.mailtrap.model.response.emails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Represents the response from sending a message.
 */
@Data
public class SendResponse {

    private boolean success;

    @JsonProperty("message_ids")
    private List<String> messageIds;

}
