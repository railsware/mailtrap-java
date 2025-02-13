package io.mailtrap.model.request.contacts;

import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateContactRequest extends AbstractModel {

  private UpdateContact contact;

}
