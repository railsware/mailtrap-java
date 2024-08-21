package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AccountsResponse {

    private long id;

    private String name;

    @JsonProperty("access_levels")
    private List<AccessLevel> accessLevels;
}
