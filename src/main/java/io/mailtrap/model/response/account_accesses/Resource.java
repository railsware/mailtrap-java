package io.mailtrap.model.response.account_accesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Resource {

    @JsonProperty("resource_id")
    private long resourceId;

    @JsonProperty("resource_type")
    private ResourceType resourceType;

    /**
     * Possible values are <p>
     * 1000 - owner <p>
     * 100 - admin <p>
     * 10 - viewer <p>
     * 1 - fallback
     */
    @JsonProperty("access_level")
    private int accessLevel;

}
