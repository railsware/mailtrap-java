package io.mailtrap.model.response.account_accesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.response.Permissions;
import lombok.Data;

import java.util.List;

@Data
public class AccountAccessResponse {

    private long id;

    private Specifier specifier;

    @JsonProperty("specifier_type")
    private SpecifierType specifierType;

    private List<Resource> resources;

    private Permissions permissions;

}
