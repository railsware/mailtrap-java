package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageHtmlAnalysisResponse {

    @JsonProperty("report")
    private HtmlAnalysisReport report;

}
