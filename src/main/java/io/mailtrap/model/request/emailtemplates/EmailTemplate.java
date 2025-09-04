package io.mailtrap.model.request.emailtemplates;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailTemplate {

  @NotNull
  @Size(min = 1, max = 255)
  private String name;

  @NotNull
  @Size(min = 1, max = 255)
  private String category;

  @NotNull
  @Size(min = 1, max = 255)
  private String subject;

  @Size(max = 10_000_000)
  @JsonProperty("body_text")
  private String bodyText;

  @Size(max = 10_000_000)
  @JsonProperty("body_html")
  private String bodyHtml;

}
