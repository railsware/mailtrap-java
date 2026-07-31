package io.mailtrap.model.request.inbound;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.EmailAttachment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Body for forwarding an inbound message. Requires at least one {@code to}
 * recipient. {@code from} is rejected for Mailtrap-hosted inboxes and required
 * for custom-domain inboxes.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InboundForwardRequest extends AbstractModel {

    @Valid
    private Address from;

    @NotEmpty
    @Valid
    private List<Address> to;

    @Valid
    private List<Address> cc;

    @Valid
    private List<Address> bcc;

    @JsonProperty("reply_to")
    @Valid
    private Address replyTo;

    private String subject;

    private String text;

    private String html;

    private String category;

    @Valid
    private List<EmailAttachment> attachments;

    private Map<String, String> headers;

    @JsonProperty("custom_variables")
    private Map<String, String> customVariables;
}
