package io.mailtrap.model.response.permissions;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AccessLevel;
import io.mailtrap.model.ResourceType;
import lombok.Data;

import java.util.List;

@Data
public class Resource {

    private long id;

    private String name;

    private ResourceType type;

    @JsonProperty("access_level")
    private AccessLevel accessLevel;

    private List<Resource> resources;

}
