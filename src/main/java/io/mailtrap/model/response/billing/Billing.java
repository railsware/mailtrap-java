package io.mailtrap.model.response.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Billing {

    @JsonProperty("cycle_start")
    private String cycleStart;

    @JsonProperty("cycle_end")
    private String cycleEnd;
}
