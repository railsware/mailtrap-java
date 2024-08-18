package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.response.account_accesses.ResourceType;
import lombok.Data;

import java.util.List;

@Data
public class Resource {

    private long id;

    private String name;

    private ResourceType type;

    @JsonProperty("access_level")
    private int accessLevel;

    private List<Resource> resources;

}
