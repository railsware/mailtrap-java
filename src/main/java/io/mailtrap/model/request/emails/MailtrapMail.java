package io.mailtrap.model.request.emails;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@FieldNameConstants
public class MailtrapMail extends AbstractModel {

    @NotNull
    @Valid
    private Address from;

    @NotEmpty
    @Valid
    private List<Address> to;

    private List<Address> cc;

    private List<Address> bcc;

    @Valid
    private List<EmailAttachment> attachments;

    /**
     * 'Content-Transfer-Encoding' header will be ignored and replaced with 'quoted-printable'
     */
    private Map<String, String> headers;

    /**
     * Values that are specific to the entire send that will be carried along with the email and its activity data
     */
    @JsonProperty("custom_variables")
    private Map<String, String> customVariables;

    /**
     * Should be used in conjunction with {@link #text} and/or {@link #html}
     */
    private String subject;

    private String text;

    private String html;

    private String category;

    /**
     * Should NOT be used if {@link #subject}, {@link #text} or {@link #html} is used
     */
    @JsonProperty("template_uuid")
    private String templateUuid;

    /**
     * Should be sent if {@link #templateUuid} is used
     */
    @JsonProperty("template_variables")
    private Map<String, Object> templateVariables;

}
