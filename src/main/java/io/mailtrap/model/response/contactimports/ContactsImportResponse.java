package io.mailtrap.model.response.contactimports;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContactsImportResponse {

  private long id;

  private ContactImportStatus status;

  @JsonProperty("created_contacts_count")
  private Long createdContactsCount;

  @JsonProperty("updated_contacts_count")
  private Long updatedContactsCount;

  @JsonProperty("contacts_over_limit_count")
  private Long contactsOverLimitCount;

}
