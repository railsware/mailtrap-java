package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AccountsResponse {

    private long id;

    private String name;

    /**
     * Possible values are<p>
     * 10 - viewer <p>
     * 100 - admin <p>
     * 1000 - account owner
     */
    @JsonProperty("access_levels")
    private List<Integer> accessLevels;
}
