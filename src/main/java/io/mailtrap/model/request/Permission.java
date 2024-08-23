package io.mailtrap.model.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.response.AccessLevel;
import io.mailtrap.model.response.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Permission {

    /**
     * The ID of the resource
     */
    @JsonProperty("resource_id")
    // TODO update type to long as soon as domain UUIDs will use bigints as identifiers
    private String resourceId;

    @JsonProperty("resource_type")
    private ResourceType resourceType;

    @JsonProperty("access_level")
    private AccessLevel accessLevel;

    /**
     * Optional parameter.
     * If true, instead of creating/updating the permission, it destroys it
     */
    @JsonProperty("_destroy")
    private boolean destroy;

}
