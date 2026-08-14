package io.mailtrap.model.response.emailcampaigns;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Reply-To address parts. Used both on request input and on the campaign response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReplyTo extends AbstractModel {

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("local_part")
    private String localPart;

    private String domain;

}
