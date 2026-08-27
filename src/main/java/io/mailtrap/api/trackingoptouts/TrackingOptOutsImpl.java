package io.mailtrap.api.trackingoptouts;

import io.mailtrap.Constants;
import io.mailtrap.api.apiresource.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.trackingoptouts.CreateTrackingOptOutRequest;
import io.mailtrap.model.request.trackingoptouts.TrackingOptOutListFilter;
import io.mailtrap.model.response.trackingoptouts.TrackingOptOut;
import io.mailtrap.model.response.trackingoptouts.TrackingOptOutListResponse;
import io.mailtrap.model.response.trackingoptouts.TrackingOptOutResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static io.mailtrap.http.RequestData.entry;

public class TrackingOptOutsImpl extends ApiResource implements TrackingOptOuts {

    private static final String BASE_PATH = "/api/tracking_opt_outs";

    public TrackingOptOutsImpl(final MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public TrackingOptOutListResponse getTrackingOptOuts(final TrackingOptOutListFilter filter) {
        final var queryParams = RequestData.buildQueryParams(
            entry("email", Optional.ofNullable(filter).map(TrackingOptOutListFilter::getEmail)),
            entry("start_time", Optional.ofNullable(filter).map(TrackingOptOutListFilter::getStartTime)),
            entry("end_time", Optional.ofNullable(filter).map(TrackingOptOutListFilter::getEndTime)),
            entry("last_id", Optional.ofNullable(filter).map(TrackingOptOutListFilter::getLastId))
        );

        return httpClient.get(
            apiHost + BASE_PATH,
            new RequestData(queryParams),
            TrackingOptOutListResponse.class
        );
    }

    @Override
    public TrackingOptOut createTrackingOptOut(final CreateTrackingOptOutRequest request) {
        return httpClient.post(
            apiHost + BASE_PATH,
            request,
            new RequestData(),
            TrackingOptOutResponse.class
        ).getData();
    }

    @Override
    public TrackingOptOut deleteTrackingOptOut(final String trackingOptOutId) {
        if (trackingOptOutId == null || trackingOptOutId.isBlank()) {
            throw new IllegalArgumentException("trackingOptOutId must not be null or blank");
        }

        return httpClient.delete(
            String.format(
                apiHost + BASE_PATH + "/%s",
                URLEncoder.encode(trackingOptOutId, StandardCharsets.UTF_8)
            ),
            new RequestData(),
            TrackingOptOut.class
        );
    }
}
