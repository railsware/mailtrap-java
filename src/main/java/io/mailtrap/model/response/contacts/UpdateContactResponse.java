package io.mailtrap.model.response.contacts;

import lombok.Data;

@Data
public class UpdateContactResponse {

    private ContactAction action;

    private UpdateContactData data;

}
