package io.mailtrap.model.response.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendingBillingInfo {

    private Plan plan;

    @JsonProperty("usage")
    private SendingUsage usage;

}
