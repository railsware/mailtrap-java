package io.mailtrap.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateData {

    @JsonProperty("name")
    @Size(min = 2, max = 100)
    private String name;

}
