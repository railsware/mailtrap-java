package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SpamScoreReport {

    @JsonProperty("ResponseCode")
    private int responseCode;

    @JsonProperty("ResponseMessage")
    private String responseMessage;

    @JsonProperty("ResponseVersion")
    private String responseVersion;

    @JsonProperty("Score")
    private double score;

    @JsonProperty("Spam")
    private boolean spam;

    @JsonProperty("Threshold")
    private double threshold;

    @JsonProperty("Details")
    private List<Map<String, String>> details;

}
