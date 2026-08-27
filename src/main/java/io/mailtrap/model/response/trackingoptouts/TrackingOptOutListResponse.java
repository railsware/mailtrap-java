package io.mailtrap.model.response.trackingoptouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Page of tracking opt-outs wrapped as {@code data}, with the cursor for the next page.
 * {@code lastId} is null when there are no more pages.
 */
@Data
public class TrackingOptOutListResponse {

    private List<TrackingOptOut> data;

    @JsonProperty("last_id")
    private String lastId;

}
