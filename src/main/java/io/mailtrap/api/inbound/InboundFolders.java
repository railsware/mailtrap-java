package io.mailtrap.api.inbound;

import io.mailtrap.model.request.inbound.CreateInboundFolderRequest;
import io.mailtrap.model.request.inbound.UpdateInboundFolderRequest;
import io.mailtrap.model.response.inbound.InboundFolder;

import java.util.List;

/**
 * Interface representing the Mailtrap Inbound Email API for managing folders.
 */
public interface InboundFolders {

    /**
     * List all inbound folders in the account.
     *
     * @return the list of inbound folders
     */
    List<InboundFolder> getList();

    /**
     * Get an inbound folder by ID.
     *
     * @param folderId the folder ID
     * @return the folder
     */
    InboundFolder getById(long folderId);

    /**
     * Create a new inbound folder.
     *
     * @param request the create request
     * @return the created folder
     */
    InboundFolder create(CreateInboundFolderRequest request);

    /**
     * Rename an inbound folder.
     *
     * @param folderId the folder ID
     * @param request  the update request
     * @return the updated folder
     */
    InboundFolder update(long folderId, UpdateInboundFolderRequest request);

    /**
     * Delete an inbound folder along with all of its inboxes.
     *
     * @param folderId the folder ID
     */
    void delete(long folderId);
}
