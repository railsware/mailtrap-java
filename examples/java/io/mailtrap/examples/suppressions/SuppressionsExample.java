package io.mailtrap.examples.suppressions;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.SendingStream;
import io.mailtrap.model.request.suppressions.CreateSuppressionRequest;

public class SuppressionsExample {

  private static final String TOKEN = System.getenv("MAILTRAP_API_KEY");
  private static final long ACCOUNT_ID = Long.parseLong(System.getenv("MAILTRAP_ACCOUNT_ID"));
  private static final long DOMAIN_ID = Long.parseLong(System.getenv("MAILTRAP_DOMAIN_ID"));
  private static final String EMAIL = "example@mailtrap.io";

  public static void main(String[] args) {
    final var config = new MailtrapConfig.Builder()
        .token(TOKEN)
        .build();

    final var client = MailtrapClientFactory.createMailtrapClient(config);
    final var suppressions = client.sendingApi().suppressions();

    // Add an email to the suppression list. Type defaults to "manual import" when omitted.
    final var created = suppressions.createSuppression(
        ACCOUNT_ID,
        CreateSuppressionRequest.builder()
            .email(EMAIL)
            .domainId(DOMAIN_ID)
            .sendingStream(SendingStream.TRANSACTIONAL)
            .build()
    );

    System.out.println(created);

    final var searchResponse = suppressions.search(ACCOUNT_ID, EMAIL);

    System.out.println(searchResponse);

    if (!searchResponse.isEmpty()) {
      final var deletedSuppression = suppressions
          .deleteSuppression(ACCOUNT_ID, searchResponse.get(0).getId());

      System.out.println(deletedSuppression);
    }
  }
}
