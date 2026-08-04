package io.mailtrap.model.response.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InboundInbox {

    private long id;

    private String name;

    private String address;

    @JsonProperty("domain_id")
    private Long domainId;
}
