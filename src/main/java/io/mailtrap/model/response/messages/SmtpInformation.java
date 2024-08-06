package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SmtpInformation {

    @JsonProperty("ok")
    private boolean ok;

    @JsonProperty("data")
    private MessageSmtpData data;

}
