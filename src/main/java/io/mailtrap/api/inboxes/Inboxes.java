package io.mailtrap.api.inboxes;

import io.mailtrap.model.request.inboxes.CreateInboxRequest;
import io.mailtrap.model.request.inboxes.UpdateInboxRequest;
import io.mailtrap.model.response.inboxes.InboxResponse;

import java.util.List;

/**
 * Interface representing the Mailtrap Testing API for interaction with inboxes
 */
public interface Inboxes {

    /**
     * Create an inbox in a project
     *
     * @param accountId unique account ID
     * @param projectId unique project ID
     * @param request   inbox request data
     * @return the newly created inbox with its attributes
     */
    InboxResponse createInbox(long accountId, long projectId, CreateInboxRequest request);

    /**
     * Get inbox attributes by inbox ID
     *
     * @param accountId unique account ID
     * @param inboxId   unique inbox ID
     * @return the attributes of the inbox
     */
    InboxResponse getInboxAttributes(long accountId, long inboxId);

    /**
     * Delete an inbox with all its emails
     *
     * @param accountId unique account ID
     * @param inboxId   unique inbox ID
     * @return the attributes of the deleted inbox
     */
    InboxResponse deleteInbox(long accountId, long inboxId);

    /**
     * Update inbox name, inbox email username
     *
     * @param accountId unique account ID
     * @param inboxId   unique inbox ID
     * @param request   request data
     * @return the attributes of the updated inbox
     */
    InboxResponse updateInbox(long accountId, long inboxId, UpdateInboxRequest request);

    /**
     * Delete all messages (emails) from inbox
     *
     * @param accountId unique account ID
     * @param inboxId   unique inbox ID
     * @return the attributes of the inbox. <b>permissions</b> returns the permissions of the token for the inbox.
     */
    InboxResponse cleanInbox(long accountId, long inboxId);

    /**
     * Mark all messages in the inbox as read
     *
     * @param accountId unique account ID
     * @param inboxId   unique inbox ID
     * @return the attributes of the inbox. <b>permissions</b> returns the permissions of the token for the inbox
     */
    InboxResponse markAsRead(long accountId, long inboxId);

    /**
     * Reset SMTP credentials of the inbox
     *
     * @param accountId unique account ID
     * @param inboxId   unique inbox ID
     * @return the attributes of the inbox. <b>permissions</b> returns the permissions of the token for the inbox
     */
    InboxResponse resetCredentials(long accountId, long inboxId);

    /**
     * Turn the email address of the inbox on/off
     *
     * @param accountId unique account ID
     * @param inboxId   unique inbox ID
     * @return the attributes of the inbox. <b>permissions</b> returns the permissions of the token for the inbox
     */
    InboxResponse enableEmailAddress(long accountId, long inboxId);

    /**
     * Reset username of email address per inbox
     *
     * @param accountId unique account ID
     * @param inboxId   unique inbox ID
     * @return the attributes of the inbox. <b>permissions</b> returns the permissions of the token for the inbox
     */
    InboxResponse resetEmailAddresses(long accountId, long inboxId);

    /**
     * Get a list of inboxes
     *
     * @param accountId unique account ID
     * @return the list of inboxes in the account to which the API token has access. <b>permissions</b> returns the permissions of the token for the inbox
     */
    List<InboxResponse> getInboxes(long accountId);
}
