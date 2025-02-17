package io.mailtrap.model.request.sendingdomains;

import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SendingDomainsSetupInstructionsRequest extends AbstractModel {

    private String email;

}
