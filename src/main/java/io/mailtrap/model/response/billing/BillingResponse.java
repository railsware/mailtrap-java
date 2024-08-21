package io.mailtrap.model.response.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BillingResponse {

    @JsonProperty("billing")
    private BillingCycleInfo billingCycleInfo;

    @JsonProperty("testing")
    private TestingBillingInfo testingBillingInfo;

    @JsonProperty("sending")
    private SendingBillingInfo sendingBillingInfo;

}
