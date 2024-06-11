package io.mailtrap.model.request;

import io.mailtrap.model.AbstractModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents an email address.
 */
@Getter
@Builder
public class Address extends AbstractModel {

    private String name;

    @Email
    @NotEmpty
    private String email;

}
