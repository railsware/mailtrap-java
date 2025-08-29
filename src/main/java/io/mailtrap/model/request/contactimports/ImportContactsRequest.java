package io.mailtrap.model.request.contactimports;

import io.mailtrap.model.AbstractModel;
import java.util.List;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImportContactsRequest extends AbstractModel {

  @Size(max = 50_000, message = "Maximum 50000 contacts per request")
  private List<Contact> contacts;

}
