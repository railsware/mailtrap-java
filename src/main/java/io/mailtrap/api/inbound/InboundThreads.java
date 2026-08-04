package io.mailtrap.api.inbound;

import io.mailtrap.model.response.inbound.InboundThread;
import io.mailtrap.model.response.inbound.InboundThreadsListResponse;

/**
 * Interface representing the Mailtrap Inbound Email API for conversation threads.
 */
public interface InboundThreads {

    /**
     * List conversation threads in an inbox.
     *
     * @param inboxId the inbox ID
     * @param lastId  pagination cursor from a previous response ({@code null} for
     *                the first page)
     * @return a page of threads
     */
    InboundThreadsListResponse list(long inboxId, String lastId);

    /**
     * Get a single thread with its messages embedded (oldest first).
     *
     * @param inboxId  the inbox ID
     * @param threadId the thread ID
     * @return the thread
     */
    InboundThread get(long inboxId, String threadId);

    /**
     * Delete a thread.
     *
     * @param inboxId  the inbox ID
     * @param threadId the thread ID
     */
    void delete(long inboxId, String threadId);
}
