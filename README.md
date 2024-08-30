![Java](https://badgen.net/badge/icon/Java?icon=java&label=) 

# Official Mailtrap Java client

This SDK offers integration with the [official API](https://api-docs.mailtrap.io/) for [Mailtrap](https://mailtrap.io).

Quickly add email sending functionality to your Java application with Mailtrap.

## Java Version

Requires JDK 11 or higher

## Usage

### Dependency

Maven dependency

```xml

<dependency>
    <groupId>io.mailtrap</groupId>
    <artifactId>mailtrap-java</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Gradle Groovy dependency

```groovy
implementation 'io.mailtrap:mailtrap-java:1.0-SNAPSHOT'
```

Gradle Kotlin DSL dependency

```kotlin
implementation("io.mailtrap:mailtrap-java:1.0-SNAPSHOT")
```

### Usage

```java
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.EmailAttachment;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.List;
import java.util.Map;

public class MailtrapJavaSDKTest {

    public static void main(String[] args) {
        Address from = new Address("sender@example.com", "John Doe");

        Address to = new Address("receiver@example.com");

        EmailAttachment attachment = EmailAttachment.builder()
                .filename("attachment.txt")
                .type("text/plain")
                // Should the Base64 encoded content of the attachment
                .content("c2FtcGxlIHRleHQgaW4gdGV4dCBmaWxl")
                .build();

        MailtrapMail mailtrapMail = MailtrapMail.builder()
                .from(from)
                .to(List.of(to))
                .subject("Sample subject")
                .text("Sample mail text")
                .headers(Map.of("X-Mailer", "Mailtrap Java SDK"))
                .customVariables(Map.of("batch_id", "some-batch-id"))
                .html("<html><body>Test HTML</body></html>")
                .attachments(List.of(attachment))
                .build();

        MailtrapClient mailtrapClient = MailtrapClientFactory.createMailtrapClient(
                new MailtrapConfig.Builder()
                        .token("<YOUR_MAILTRAP_TOKEN>")
                        .build());

        try {
            System.out.println(mailtrapClient.send(mailtrapMail));
        } catch (Exception e) {
            System.out.println("Caught exception : " + e);
        }

        // OR send email to the Mailtrap Sandbox

        try {
            long inboxId = 1000001L;

            // Either instantiate a new client
            MailtrapClient sandboxClient = MailtrapClientFactory.createMailtrapClient(
                    new MailtrapConfig.Builder()
                            .sandbox(true)
                            .inboxId(inboxId)
                            .token("<YOUR_MAILTRAP_TOKEN>")
                            .build());

            System.out.println(sandboxClient.send(mailtrapMail));

            // Or reuse already created client
            mailtrapClient.switchToEmailTestingApi(inboxId);

            System.out.println(mailtrapClient.send(mailtrapMail));

            // Or use directly Testing API to send email to Sandbox
            System.out.println(mailtrapClient.testingApi().emails().send(mailtrapMail, inboxId));
        } catch (Exception e) {
            System.out.println("Caught exception : " + e);
        }
    }
}
```

## Contributing

Bug reports and pull requests are welcome on [GitHub](https://github.com/railsware/mailtrap-java). This project is intended to be a safe, welcoming space for collaboration, and contributors are expected to adhere to the [code of conduct](CODE_OF_CONDUCT.md).

## License

The package is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).

## Code of Conduct

Everyone interacting in the Mailtrap project's codebases, issue trackers, chat rooms and mailing lists is expected to follow the [code of conduct](CODE_OF_CONDUCT.md).
