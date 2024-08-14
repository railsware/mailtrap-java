package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForwardMessageResponse {

    @JsonProperty("message")
    private String message;

}
