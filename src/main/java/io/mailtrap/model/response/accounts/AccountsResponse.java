package io.mailtrap.model.response.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AccessLevel;
import lombok.Data;

import java.util.List;

@Data
public class AccountsResponse {

    private long id;

    private String name;

    @JsonProperty("access_levels")
    private List<AccessLevel> accessLevels;

}
