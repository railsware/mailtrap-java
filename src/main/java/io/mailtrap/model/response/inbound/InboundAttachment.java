package io.mailtrap.model.response.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

/**
 * Attachment metadata on a received message. {@code downloadUrl} and
 * {@code downloadUrlExpiresAt} are only populated on get-by-id and thread responses.
 */
@Data
public class InboundAttachment {

    @JsonProperty("attachment_id")
    private String attachmentId;

    private Integer size;

    private String filename;

    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("content_disposition")
    private String contentDisposition;

    @JsonProperty("content_id")
    private String contentId;

    @JsonProperty("download_url")
    private String downloadUrl;

    @JsonProperty("download_url_expires_at")
    private OffsetDateTime downloadUrlExpiresAt;
}
