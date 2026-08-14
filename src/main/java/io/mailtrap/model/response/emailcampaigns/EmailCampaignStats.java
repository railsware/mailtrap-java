package io.mailtrap.model.response.emailcampaigns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Aggregated campaign performance metrics. Counts and rates are {@code 0} when the campaign
 * has not been started.
 */
@Data
public class EmailCampaignStats {

    @JsonProperty("delivery_count")
    private Integer deliveryCount;

    @JsonProperty("open_count")
    private Integer openCount;

    @JsonProperty("click_count")
    private Integer clickCount;

    @JsonProperty("bounce_count")
    private Integer bounceCount;

    @JsonProperty("unsubscription_count")
    private Integer unsubscriptionCount;

    @JsonProperty("sent_count")
    private Integer sentCount;

    @JsonProperty("spam_count")
    private Integer spamCount;

    @JsonProperty("delivery_rate")
    private Double deliveryRate;

    @JsonProperty("open_rate")
    private Double openRate;

    @JsonProperty("click_rate")
    private Double clickRate;

    @JsonProperty("bounce_rate")
    private Double bounceRate;

    @JsonProperty("spam_rate")
    private Double spamRate;

    @JsonProperty("unsubscription_rate")
    private Double unsubscriptionRate;

}
