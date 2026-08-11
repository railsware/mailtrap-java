package io.mailtrap.api.emailcampaigns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Aggregation window for email campaign statistics. Both fields are optional; {@code null}
 * fields are omitted from the query string and the window defaults to the whole period since
 * the campaign was last started.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailCampaignStatsFilter {

    /**
     * Start of the aggregation window (inclusive), in {@code YYYY-MM-DD} format.
     */
    private String startDate;

    /**
     * End of the aggregation window (inclusive), in {@code YYYY-MM-DD} format.
     */
    private String endDate;
}
