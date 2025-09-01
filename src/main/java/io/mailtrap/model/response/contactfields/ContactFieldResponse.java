package io.mailtrap.model.response.contactfields;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.ContactFieldDataType;
import lombok.Data;

@Data
public class ContactFieldResponse {

  private long id;

  private String name;

  @JsonProperty("data_type")
  private ContactFieldDataType dataType;

  @JsonProperty("merge_tag")
  private String mergeTag;

}
