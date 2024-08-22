package io.mailtrap.model.response.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TestingBillingInfo {

    private Plan plan;

    @JsonProperty("usage")
    private TestingUsage usage;

}
