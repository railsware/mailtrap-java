package io.mailtrap.model.request.trackingoptouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateTrackingOptOutRequest extends AbstractModel {

    private String email;

    @JsonProperty("domain_id")
    private Long domainId;

}
