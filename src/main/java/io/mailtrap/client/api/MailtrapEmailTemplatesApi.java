package io.mailtrap.client.api;

import io.mailtrap.api.emailtemplates.EmailTemplates;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class MailtrapEmailTemplatesApi {
  private final EmailTemplates emailTemplates;
}
