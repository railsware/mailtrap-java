package io.mailtrap.model.response.contactimports;

import lombok.Data;

@Data
public class ImportContactResponse {

  private long id;

  private ContactImportStatus status;

}
