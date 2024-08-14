package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageSmtpData {

    @JsonProperty("mail_from_addr")
    private String mailFromAddr;

    @JsonProperty("client_ip")
    private String clientIp;
}
