package io.mailtrap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @JsonProperty("can_read")
    private boolean canRead;

    @JsonProperty("can_update")
    private boolean canUpdate;

    @JsonProperty("can_destroy")
    private boolean canDestroy;

    @JsonProperty("can_leave")
    private boolean canLeave;

}
