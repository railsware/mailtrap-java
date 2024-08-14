package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class MessageHeadersResponse {

    @JsonProperty("headers")
    private Map<String, String> headers;
}
