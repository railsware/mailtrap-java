package io.mailtrap.model.response.projects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.response.inboxes.InboxResponse;
import io.mailtrap.model.response.Permission;
import lombok.Data;

import java.util.List;

@Data
public class ProjectsResponse {

    private long id;

    private String name;

    @JsonProperty("share_links")
    private ShareLinks shareLinks;

    private List<InboxResponse> inboxes;

    private Permission permission;

}
