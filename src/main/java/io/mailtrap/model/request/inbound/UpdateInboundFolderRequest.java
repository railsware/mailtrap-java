package io.mailtrap.model.request.inbound;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.mailtrap.model.AbstractModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateInboundFolderRequest extends AbstractModel {

    private String name;
}
