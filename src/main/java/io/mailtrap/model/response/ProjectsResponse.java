package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProjectsResponse {

    private long id;

    private String name;

    @JsonProperty("share_links")
    private ShareLinks shareLinks;

    private List<InboxResponse> inboxes;

    private Permissions permissions;
}
