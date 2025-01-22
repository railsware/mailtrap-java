package io.mailtrap.model.response.contacts;

import lombok.Data;

@Data
public class CreateContactResponse {

    private String id;

    private ContactStatus status;

}
