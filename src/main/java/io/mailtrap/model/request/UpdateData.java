package io.mailtrap.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpdateData extends CreateData {

    @JsonProperty("email_username")
    private String emailUsername;

    public UpdateData(String name, String emailUsername) {
        super(name);
        this.emailUsername=emailUsername;
    }
}
