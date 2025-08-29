package io.mailtrap.model.response.contactimports;

import lombok.Data;

@Data
public class CreateContactsImportResponse {

  private long id;

  private ContactImportStatus status;

}
