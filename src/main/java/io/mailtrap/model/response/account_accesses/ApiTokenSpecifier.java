package io.mailtrap.model.response.account_accesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiTokenSpecifier extends Specifier {

    private String name;

    @JsonProperty("author_name")
    private String authorName;

    private String token;

    @JsonProperty("expires_at")
    private String expiresAt;

}
