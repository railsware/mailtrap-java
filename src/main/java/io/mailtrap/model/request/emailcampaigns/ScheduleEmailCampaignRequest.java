package io.mailtrap.model.request.emailcampaigns;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * Request body for scheduling an email campaign.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEmailCampaignRequest extends AbstractModel {

    /**
     * When to send the campaign. Must be in the future and no more than 1 month ahead.
     *
     * <p>Serialized as an ISO-8601 string; the SDK's mapper otherwise renders date-times as
     * numeric timestamps, which the API rejects.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime datetime;

}
