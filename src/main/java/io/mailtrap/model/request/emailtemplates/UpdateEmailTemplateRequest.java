package io.mailtrap.model.request.emailtemplates;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateEmailTemplateRequest extends AbstractModel {

  @Valid
  @NotNull
  @JsonProperty("email_template")
  private EmailTemplate emailTemplate;

}
