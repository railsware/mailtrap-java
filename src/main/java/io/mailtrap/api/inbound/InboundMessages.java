package io.mailtrap.api.inbound;

import io.mailtrap.model.request.inbound.InboundForwardRequest;
import io.mailtrap.model.request.inbound.InboundReplyRequest;
import io.mailtrap.model.response.inbound.InboundMessage;
import io.mailtrap.model.response.inbound.InboundMessagesListResponse;
import io.mailtrap.model.response.inbound.InboundSendResult;

/**
 * Interface representing the Mailtrap Inbound Email API for received messages.
 */
public interface InboundMessages {

    /**
     * List received messages in an inbox.
     *
     * @param inboxId the inbox ID
     * @param lastId  pagination cursor from a previous response ({@code null} for
     *                the first page)
     * @return a page of messages
     */
    InboundMessagesListResponse list(long inboxId, String lastId);

    /**
     * Get a single message with its body and attachment download URLs.
     *
     * @param inboxId   the inbox ID
     * @param messageId the message ID
     * @return the message
     */
    InboundMessage get(long inboxId, String messageId);

    /**
     * Delete a message.
     *
     * @param inboxId   the inbox ID
     * @param messageId the message ID
     */
    void delete(long inboxId, String messageId);

    /**
     * Reply to a message (to the original sender). Sends a real email.
     *
     * @param inboxId   the inbox ID
     * @param messageId the message ID
     * @param request   the reply body
     * @return the send result
     */
    InboundSendResult reply(long inboxId, String messageId, InboundReplyRequest request);

    /**
     * Reply to a message and copy the original's other recipients. Sends a real email.
     *
     * @param inboxId   the inbox ID
     * @param messageId the message ID
     * @param request   the reply body
     * @return the send result
     */
    InboundSendResult replyAll(long inboxId, String messageId, InboundReplyRequest request);

    /**
     * Forward a message to new recipients (at least one {@code to} is required).
     * Sends a real email.
     *
     * @param inboxId   the inbox ID
     * @param messageId the message ID
     * @param request   the forward body
     * @return the send result
     */
    InboundSendResult forward(long inboxId, String messageId, InboundForwardRequest request);
}
