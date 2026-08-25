package io.mailtrap.model.request.sendingdomains;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateSendingDomainRequest extends AbstractModel {

    @JsonProperty("sending_domain")
    private SendingDomainData sendingDomain;

    @Getter
    @Builder
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SendingDomainData {

        @JsonProperty("open_tracking_enabled")
        private Boolean openTrackingEnabled;

        @JsonProperty("click_tracking_enabled")
        private Boolean clickTrackingEnabled;

        @JsonProperty("tracking_opt_out_enabled")
        private Boolean trackingOptOutEnabled;

        @JsonProperty("auto_unsubscribe_link_enabled")
        private Boolean autoUnsubscribeLinkEnabled;

        @JsonProperty("inbound_enabled")
        private Boolean inboundEnabled;

    }

}
