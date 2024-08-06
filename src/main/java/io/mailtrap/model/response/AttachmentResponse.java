package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AttachmentResponse {
    private long id;

    @JsonProperty("message_id")
    private long messageId;

    private String filename;

    @JsonProperty("attachment_type")
    private String attachmentType;

    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("content_id")
    private String contentId;

    @JsonProperty("transfer_encoding")
    private String transferEncoding;

    @JsonProperty("attachment_size")
    private int attachmentSize;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("attachment_human_size")
    private String attachmentHumanSize;

    @JsonProperty("download_path")
    private String downloadPath;

}
