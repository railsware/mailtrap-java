package io.mailtrap.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
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
    private List<Attachment> attachments;

    private Map<String, String> headers;

    @JsonProperty("custom_variables")
    private Map<String, String> customVariables;

    @NotEmpty
    private String subject;

    private String text;

    private String html;

    private String category;

}
