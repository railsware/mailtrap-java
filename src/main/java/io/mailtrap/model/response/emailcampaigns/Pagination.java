package io.mailtrap.model.response.emailcampaigns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Page-token pagination metadata returned with a list of email campaigns.
 */
@Data
public class Pagination {

    /**
     * Current page number.
     */
    private Integer token;

    /**
     * Previous page number, or {@code null} on the first page.
     */
    @JsonProperty("prev_token")
    private Integer prevToken;

    /**
     * Next page number, or {@code null} on the last page.
     */
    @JsonProperty("next_token")
    private Integer nextToken;

    @JsonProperty("first_url")
    private String firstUrl;

    @JsonProperty("prev_url")
    private String prevUrl;

    @JsonProperty("current_url")
    private String currentUrl;

    @JsonProperty("next_url")
    private String nextUrl;

}
