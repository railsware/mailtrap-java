package io.mailtrap.model.request.inbound;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Omit {@code domainId} for a Mailtrap-hosted inbox; pass it to create a
 * custom-domain (catch-all) inbox.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateInboundInboxRequest extends AbstractModel {

    private String name;

    @JsonProperty("domain_id")
    private Long domainId;
}
