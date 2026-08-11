package io.mailtrap.model.response.emailcampaigns;

import io.mailtrap.model.response.Pagination;
import lombok.Data;

import java.util.List;

/**
 * Paginated list of email campaigns, wrapped as {@code data} alongside page-token pagination
 * metadata.
 */
@Data
public class EmailCampaignListResponse {

    private List<EmailCampaign> data;

    private Pagination pagination;

}
