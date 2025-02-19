package io.mailtrap.model.response.accountaccesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserSpecifier extends Specifier {

    private String email;

    private String name;

    @JsonProperty("two_factor_authentication_enabled")
    private boolean twoFactorAuthenticationEnabled;
}
