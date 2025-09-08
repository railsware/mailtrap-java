package io.mailtrap.model.request.inboxes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateInboxRequest extends AbstractModel {

    @Valid
    @JsonProperty("inbox")
    private InboxCreateData inboxCreateData;

    @Getter
    @AllArgsConstructor
    public static class InboxCreateData {

        @JsonProperty("name")
        @Size(min = 2, max = 100)
        private String name;

    }

}
