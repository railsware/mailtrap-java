package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Resource {

    @JsonProperty("resource_id")
    private long resourceId;

    @JsonProperty("resource_type")
    private String resourceType;

    @JsonProperty("access_level")
    private int accessLevel;

}
