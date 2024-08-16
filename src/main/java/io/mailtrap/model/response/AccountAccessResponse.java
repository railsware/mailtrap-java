package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AccountAccessResponse {

    private long id;

    @JsonProperty("specifier_type")
    private SpecifierType specifierType;

    private List<Resource> resources;

    private Permissions permissions;

}
