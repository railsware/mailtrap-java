package io.mailtrap.model.response.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BillingCycleInfo {

    @JsonProperty("cycle_start")
    private OffsetDateTime cycleStart;

    @JsonProperty("cycle_end")
    private OffsetDateTime cycleEnd;

}
