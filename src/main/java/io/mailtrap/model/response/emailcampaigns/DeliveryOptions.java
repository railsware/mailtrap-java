package io.mailtrap.model.response.emailcampaigns;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Delivery throttling options. Used both on update input and on the campaign response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryOptions extends AbstractModel {

    @JsonProperty("emails_per_hour")
    private Integer emailsPerHour;

}
