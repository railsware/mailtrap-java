package io.mailtrap.model.request.account_accesses;

import io.mailtrap.api.messages.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListMessagesQueryParams {

    /**
     * A page of records before `last_id` is returned. Overrides page if both are given
     */
    private Long lastId;

    /**
     * Page number for paginated results
     */
    private Integer page;

    /**
     * Search query string. Matches `subject`, `to_email`, and `to_name`
     */
    private String search;

    /**
     * Creates an instance of {@link ListMessagesQueryParams} with no query parameters set.
     * This is useful when you want to call the {@link Messages#getMessages} method without any optional parameters.
     *
     * @return A new instance with no parameters set
     */
    public static ListMessagesQueryParams empty() {
        return new ListMessagesQueryParams();
    }
}
