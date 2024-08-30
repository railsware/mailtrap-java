package io.mailtrap.model.request.emails;

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
public class EmailAttachment extends AbstractModel {

    /**
     * The Base64 encoded content of the attachment
     */
    @NotEmpty
    private String content;

    /**
     * The MIME type of the content
     */
    private String type;

    @NotEmpty
    private String filename;

    /**
     * The attachment's content-disposition
     */
    private String disposition;

    /**
     * The attachment's content ID.
     * This is used when the disposition is set to “inline” and the attachment is an image,
     * allowing the file to be displayed within the body of your email.
     */
    @JsonProperty("content_id")
    private String contentId;

}
