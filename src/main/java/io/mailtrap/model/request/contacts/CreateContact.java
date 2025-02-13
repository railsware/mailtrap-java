package io.mailtrap.model.request.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class CreateContact {

    private String email;

    private Map<String, Object> fields;

    @JsonProperty("list_ids")
    private List<Long> listIds;
}
