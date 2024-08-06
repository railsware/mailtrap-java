package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class HtmlAnalysisEmailClients {

    @JsonProperty("desktop")
    private List<String> desktop;

    @JsonProperty("mobile")
    private List<String> mobile;

    @JsonProperty("web")
    private List<String> web;

}
