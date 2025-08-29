package io.mailtrap.model.request.contactfields;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateContactFieldRequest extends AbstractModel {

  @Size(max = 80)
  private String name;

  @Size(max = 80)
  @JsonProperty("merge_tag")
  private String mergeTag;

}
