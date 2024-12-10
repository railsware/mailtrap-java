package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Report {

    private String name;

    private String url;

    @JsonProperty("in_black_list")
    private boolean inBlackList;
}
