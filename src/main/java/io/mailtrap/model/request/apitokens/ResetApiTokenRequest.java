package io.mailtrap.model.request.apitokens;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetApiTokenRequest extends AbstractModel {

    /**
     * Optional token expiration as an ISO 8601 date-time. Omit (or leave null) for the server
     * default (a 1-year default is being rolled out). Use {@link TokenExpiration#never()} for
     * a token that never expires. Past or more-than-5-years-ahead values are rejected with 422.
     */
    @JsonProperty("expires_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TokenExpiration expiresAt;

}
