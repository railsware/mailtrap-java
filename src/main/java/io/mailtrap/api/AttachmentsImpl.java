package io.mailtrap.api;

import io.mailtrap.Constants;
import io.mailtrap.api.abstractions.Attachments;
import io.mailtrap.api.abstractions.classes.ApiResource;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.response.AttachmentResponse;

import java.util.List;
import java.util.Optional;

import static io.mailtrap.http.RequestData.entry;

public class AttachmentsImpl extends ApiResource implements Attachments {

    public AttachmentsImpl(MailtrapConfig config) {
        super(config);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public AttachmentResponse getSingleAttachment(long accountId, int inboxId, long messageId, long attachmentId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/attachments/%s", accountId, inboxId, messageId, attachmentId),
                new RequestData(),
                AttachmentResponse.class);
    }

    @Override
    public List<AttachmentResponse> getAttachments(long accountId, int inboxId, long messageId, String attachmentType) {
        var queryParams = RequestData.buildQueryParams(
                entry("attachment_type", Optional.ofNullable(attachmentType)));

        return httpClient.getList(
                String.format(apiHost + "/api/accounts/%s/inboxes/%s/messages/%s/attachments", accountId, inboxId, messageId),
                new RequestData(queryParams),
                AttachmentResponse.class);
    }
}
