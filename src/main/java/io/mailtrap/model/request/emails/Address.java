package io.mailtrap.model.request.emails;

import io.mailtrap.model.AbstractModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

/**
 * Represents an email address.
 */
@Getter
public class Address extends AbstractModel {

    private String name;

    @Email
    @NotEmpty
    private final String email;

    public Address(String email) {
        this.email = email;
    }

    public Address(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
