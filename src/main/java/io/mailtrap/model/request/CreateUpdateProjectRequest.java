package io.mailtrap.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUpdateProjectRequest extends AbstractModel {

    @JsonProperty("project")
    private CreateData project;

}
