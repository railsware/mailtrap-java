package io.mailtrap.model.response.trackingoptouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrackingOptOut {

    private String id;

    private String email;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("domain_name")
    private String domainName;

}
