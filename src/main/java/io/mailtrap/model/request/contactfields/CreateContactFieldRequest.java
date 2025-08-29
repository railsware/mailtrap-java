package io.mailtrap.model.request.contactfields;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import io.mailtrap.model.ContactFieldDataType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateContactFieldRequest extends AbstractModel {

  @Size(max = 80)
  private String name;

  @JsonProperty("data_type")
  private ContactFieldDataType dataType;

  @Size(max = 80)
  @JsonProperty("merge_tag")
  private String mergeTag;

}
