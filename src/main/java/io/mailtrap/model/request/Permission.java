package io.mailtrap.model.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.response.account_accesses.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Permission {

    /**
     * The ID of the resource
     */
    @JsonProperty("resource_id")
    private String resourceId;

    /**
     * See {@link ResourceType}
     */
    @JsonProperty("resource_type")
    private String resourceType;

    /**
     * Can be admin (100) or viewer (10)
     */
    @JsonProperty("access_level")
    private String accessLevel;

    /**
     * Optional parameter.
     * If true, instead of creating/updating the permission, it destroys it
     */
    @JsonProperty("_destroy")
    private String destroy;

}
