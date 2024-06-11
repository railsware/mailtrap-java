package io.mailtrap.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an attachment in an email.
 */
@Getter
@Setter
@Builder
public class Attachment extends AbstractModel {

    @NotEmpty
    private String content;

    private String type;

    @NotEmpty
    private String filename;

    private String disposition;

    @JsonProperty("content_id")
    private String contentId;

}
