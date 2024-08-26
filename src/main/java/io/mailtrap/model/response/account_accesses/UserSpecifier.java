package io.mailtrap.model.response.account_accesses;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserSpecifier extends Specifier {

    private String email;

    private String name;

}
