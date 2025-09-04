package io.mailtrap.api.emailtemplates;

import io.mailtrap.Constants;
import io.mailtrap.CustomValidator;
import io.mailtrap.api.apiresource.ApiResourceWithValidation;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.emailtemplates.CreateEmailTemplateRequest;
import io.mailtrap.model.request.emailtemplates.UpdateEmailTemplateRequest;
import io.mailtrap.model.response.emailtemplates.EmailTemplateResponse;

import java.util.List;

public class EmailTemplatesImpl extends ApiResourceWithValidation implements EmailTemplates {

  public EmailTemplatesImpl(MailtrapConfig config, CustomValidator customValidator) {
    super(config, customValidator);
    this.apiHost = Constants.GENERAL_HOST;
  }

  @Override
  public List<EmailTemplateResponse> getAllTemplates(long accountId) {
    return
        httpClient.getList(
            String.format(apiHost + "/api/accounts/%d/email_templates", accountId),
            new RequestData(),
            EmailTemplateResponse.class
        );
  }

  @Override
  public EmailTemplateResponse createEmailTemplate(long accountId, CreateEmailTemplateRequest request) {
    validateRequestBodyAndThrowException(request);

    return
        httpClient.post(
            String.format(apiHost + "/api/accounts/%d/email_templates", accountId),
            request,
            new RequestData(),
            EmailTemplateResponse.class
        );
  }

  @Override
  public EmailTemplateResponse getEmailTemplate(long accountId, long emailTemplateId) {
    return
        httpClient.get(
            String.format(apiHost + "/api/accounts/%d/email_templates/%d", accountId, emailTemplateId),
            new RequestData(),
            EmailTemplateResponse.class
        );
  }

  @Override
  public EmailTemplateResponse updateEmailTemplate(long accountId, long emailTemplateId, UpdateEmailTemplateRequest request) {
    validateRequestBodyAndThrowException(request);

    return
        httpClient.patch(
            String.format(apiHost + "/api/accounts/%d/email_templates/%d", accountId, emailTemplateId),
            request,
            new RequestData(),
            EmailTemplateResponse.class
        );
  }

  @Override
  public void deleteEmailTemplate(long accountId, long emailTemplateId) {
    httpClient
        .delete(
            String.format(apiHost + "/api/accounts/%d/email_templates/%d", accountId, emailTemplateId),
            new RequestData(),
            Void.class
        );
  }
}
