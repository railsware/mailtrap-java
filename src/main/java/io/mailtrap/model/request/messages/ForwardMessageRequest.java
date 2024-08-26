package io.mailtrap.model.request.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ForwardMessageRequest extends AbstractModel {

    @JsonProperty("email")
    private String email;

}
