package io.mailtrap.model.response.contactimports;

import lombok.Data;

@Data
public class ImportContactsResponse {

  private long id;

  private ContactImportStatus status;

}
