package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HtmlAnalysisError {

    @JsonProperty("error_line")
    private int errorLine;

    @JsonProperty("rule_name")
    private String ruleName;

    @JsonProperty("email_clients")
    private HtmlAnalysisEmailClients emailClients;

}
