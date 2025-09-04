package io.mailtrap.model.request.emailtemplates;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateEmailTemplateRequest extends AbstractModel {

  @Valid
  @NotNull
  @JsonProperty("email_template")
  private EmailTemplate emailTemplate;

}
