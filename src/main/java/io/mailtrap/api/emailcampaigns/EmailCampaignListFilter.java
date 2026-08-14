package io.mailtrap.api.emailcampaigns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Filtering and pagination parameters for listing email campaigns. All fields are optional;
 * {@code null} fields are omitted from the query string.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailCampaignListFilter {

    /**
     * Number of campaigns per page (max 100, default 50).
     */
    private Integer perPage;

    /**
     * Filter campaigns by name.
     */
    private String search;

    /**
     * Page number to retrieve (page-token pagination, default 1).
     */
    private Integer token;
}
