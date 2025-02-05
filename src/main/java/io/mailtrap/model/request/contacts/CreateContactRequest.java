package io.mailtrap.model.request.contacts;

import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateContactRequest extends AbstractModel {

    private Contact contact;

}
