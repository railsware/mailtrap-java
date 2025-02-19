package io.mailtrap.model.response.accountaccesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AccessLevel;
import io.mailtrap.model.ResourceType;
import lombok.Data;

@Data
public class Resource {

    @JsonProperty("resource_id")
    private long resourceId;

    @JsonProperty("resource_type")
    private ResourceType resourceType;

    @JsonProperty("access_level")
    private AccessLevel accessLevel;

}
