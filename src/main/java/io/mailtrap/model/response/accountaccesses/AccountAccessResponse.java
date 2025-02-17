package io.mailtrap.model.response.accountaccesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.mailtrap.model.response.Permission;
import io.mailtrap.serialization.AccountAccessResponseDeserializer;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = AccountAccessResponseDeserializer.class)
public class AccountAccessResponse {

    private long id;

    private Specifier specifier;

    @JsonProperty("specifier_type")
    private SpecifierType specifierType;

    private List<Resource> resources;

    private Permission permission;

}
