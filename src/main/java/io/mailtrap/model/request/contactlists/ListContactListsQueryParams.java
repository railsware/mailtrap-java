package io.mailtrap.model.request.contactlists;

import io.mailtrap.api.contactlists.ContactLists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListContactListsQueryParams {

    /**
     * Filter contact lists by name (case-insensitive prefix match)
     */
    private String search;

    /**
     * Creates an instance of {@link ListContactListsQueryParams} with no query parameters set.
     * This is useful when you want to call the {@link ContactLists#findAll} method without any optional parameters.
     *
     * @return A new instance with no parameters set
     */
    public static ListContactListsQueryParams empty() {
        return new ListContactListsQueryParams();
    }
}
