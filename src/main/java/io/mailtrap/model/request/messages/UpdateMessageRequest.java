package io.mailtrap.model.request.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMessageRequest extends AbstractModel {

    @JsonProperty("message")
    private MessageUpdateData message;

    @Getter
    @AllArgsConstructor
    public static class MessageUpdateData {

        @JsonProperty("is_read")
        private String isRead;

    }
}
