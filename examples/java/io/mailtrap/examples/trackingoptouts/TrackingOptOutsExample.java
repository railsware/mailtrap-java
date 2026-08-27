package io.mailtrap.examples.trackingoptouts;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.trackingoptouts.CreateTrackingOptOutRequest;
import io.mailtrap.model.request.trackingoptouts.TrackingOptOutListFilter;

public class TrackingOptOutsExample {

  private static final String TOKEN = System.getenv("MAILTRAP_API_KEY");
  private static final long DOMAIN_ID = Long.parseLong(System.getenv("MAILTRAP_DOMAIN_ID"));
  private static final String EMAIL = "tracked@example.com";

  public static void main(String[] args) {
    final var config = new MailtrapConfig.Builder()
        .token(TOKEN)
        .build();

    final var client = MailtrapClientFactory.createMailtrapClient(config);
    final var trackingOptOuts = client.sendingApi().trackingOptOuts();

    // Opt an email out of open and click tracking for a sending domain
    final var created = trackingOptOuts.createTrackingOptOut(
        CreateTrackingOptOutRequest.builder()
            .email(EMAIL)
            .domainId(DOMAIN_ID)
            .build()
    );

    System.out.println(created);

    // Get tracking opt-outs (up to 1000 per request)
    var page = trackingOptOuts.getTrackingOptOuts(null);
    System.out.println(page.getData());

    // Filter by email and creation time
    System.out.println(trackingOptOuts.getTrackingOptOuts(
        TrackingOptOutListFilter.builder()
            .email(EMAIL)
            .startTime("2025-01-01T00:00:00Z")
            .endTime("2025-12-31T23:59:59Z")
            .build()
    ));

    // Page through the full list, following the cursor
    while (page.getLastId() != null) {
      page = trackingOptOuts.getTrackingOptOuts(
          TrackingOptOutListFilter.builder().lastId(page.getLastId()).build()
      );
      System.out.println(page.getData());
    }

    // Remove an email from the tracking opt-out list. Returns the deleted record.
    System.out.println(trackingOptOuts.deleteTrackingOptOut(created.getId()));
  }
}
