package io.mailtrap.model.request.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Contact {

    private String email;

    private Map<String, String> fields;

    @JsonProperty("list_ids")
    private List<Long> listIds;
}
