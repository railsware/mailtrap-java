package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class HtmlAnalysisReport {

    @JsonProperty("status")
    private String status;

    @JsonProperty("errors")
    private List<HtmlAnalysisError> errors;

}
