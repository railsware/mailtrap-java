package io.mailtrap.model.request.contactimports;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Contact {

  private String email;

  private Map<String, Object> fields;

  @JsonProperty("list_ids_included")
  private List<Long> listIdsIncluded;

  @JsonProperty("list_ids_excluded")
  private List<Long> listIdsExcluded;
}
