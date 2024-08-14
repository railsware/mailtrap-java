package io.mailtrap.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMessageData {

    @JsonProperty("is_read")
    private String isRead;

}
