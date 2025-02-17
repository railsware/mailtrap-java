package io.mailtrap.model.response.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class UpdateContactData {

  private String id;

  private Map<String, Object> fields;

  @JsonProperty("list_ids")
  private List<Long> listIds;

  private ContactStatus status;

}
