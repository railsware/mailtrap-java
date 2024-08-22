package io.mailtrap.model.response.account_accesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.response.AccessLevel;
import io.mailtrap.model.response.ResourceType;
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
