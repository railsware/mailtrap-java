package io.mailtrap.api.inbound;

import io.mailtrap.model.request.inbound.CreateInboundInboxRequest;
import io.mailtrap.model.request.inbound.UpdateInboundInboxRequest;
import io.mailtrap.model.response.inbound.InboundInbox;

import java.util.List;

/**
 * Interface representing the Mailtrap Inbound Email API for managing inboxes
 * within a folder.
 */
public interface InboundInboxes {

    /**
     * List all inboxes in an inbound folder.
     *
     * @param folderId the folder ID
     * @return the list of inboxes
     */
    List<InboundInbox> getList(long folderId);

    /**
     * Get an inbound inbox by ID.
     *
     * @param folderId the folder ID
     * @param inboxId  the inbox ID
     * @return the inbox
     */
    InboundInbox getById(long folderId, long inboxId);

    /**
     * Create a new inbound inbox in a folder.
     *
     * @param folderId the folder ID
     * @param request  the create request
     * @return the created inbox
     */
    InboundInbox create(long folderId, CreateInboundInboxRequest request);

    /**
     * Rename an inbound inbox.
     *
     * @param folderId the folder ID
     * @param inboxId  the inbox ID
     * @param request  the update request
     * @return the updated inbox
     */
    InboundInbox update(long folderId, long inboxId, UpdateInboundInboxRequest request);

    /**
     * Delete an inbound inbox.
     *
     * @param folderId the folder ID
     * @param inboxId  the inbox ID
     */
    void delete(long folderId, long inboxId);
}
