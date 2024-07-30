package io.mailtrap.api.abstractions;

import io.mailtrap.model.response.AttachmentResponse;

import java.util.List;

public interface Attachments {

    AttachmentResponse getSingleAttachment(long accountId, int inboxId, long messageId, long attachmentId);

    List<AttachmentResponse> getAttachments(long accountId, int inboxId, long messageId, String attachmentType);

}
