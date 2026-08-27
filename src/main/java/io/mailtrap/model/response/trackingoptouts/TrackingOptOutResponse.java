package io.mailtrap.model.response.trackingoptouts;

import lombok.Data;

/**
 * Single tracking opt-out wrapped as {@code data}.
 */
@Data
public class TrackingOptOutResponse {

    private TrackingOptOut data;

}
