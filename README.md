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
    <version>0.0.1</version>
</dependency>
```

Gradle Groovy dependency

```groovy
implementation 'io.mailtrap:mailtrap-java:0.0.1'
```

Gradle Kotlin DSL dependency

```kotlin
implementation("io.mailtrap:mailtrap-java:0.0.1")
```

## Usage

### Minimal

```java
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.List;

public class MailtrapJavaSDKTest {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final String SENDER_EMAIL = "sender@domain.com";
    private static final String RECIPIENT_EMAIL = "recipient@domain.com";

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        final MailtrapMail mail = MailtrapMail.builder()
                .from(new Address(SENDER_EMAIL))
                .to(List.of(new Address(RECIPIENT_EMAIL)))
                .subject("Hello from Mailtrap Sending!")
                .text("Welcome to Mailtrap Sending!")
                .build();

        try {
            System.out.println(client.send(mail));
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
                            .token(TOKEN)
                            .build());

            System.out.println(sandboxClient.send(mail));

            // Or reuse already created client
            client.switchToEmailTestingApi(inboxId);

            System.out.println(client.send(mail));

            // Or use directly Testing API to send email to Sandbox
            System.out.println(client.testingApi().emails().send(mail, inboxId));
        } catch (Exception e) {
            System.out.println("Caught exception : " + e);
        }
    }
}
```

Refer to the [`examples`](examples) folder for the source code of this and other advanced examples.

### API Reference

You can find the API reference [here](https://railsware.github.io/mailtrap-java/index.html).

### General API

- [List User & Invite account accesses](examples/java/io/mailtrap/examples/general/AccountAccess.java)
- [Remove account access](examples/java/io/mailtrap/examples/general/Accounts.java)
- [Permissions](examples/java/io/mailtrap/examples/general/Permissions.java)
- [Current billing usage cycle](examples/java/io/mailtrap/examples/general/Billing.java)

### Sending API

- [Advanced](examples/java/io/mailtrap/examples/sending/Everything.java)
- [Minimal](examples/java/io/mailtrap/examples/sending/Minimal.java)
- [Error handling](examples/java/io/mailtrap/examples/sending/Errors.java)
- [Send email using template](examples/java/io/mailtrap/examples/sending/Template.java)

### Email testing API

- [Attachments](examples/java/io/mailtrap/examples/testing/Attachments.java)
- [Inboxes](examples/java/io/mailtrap/examples/testing/Inboxes.java)
- [Messages](examples/java/io/mailtrap/examples/testing/Messages.java)
- [Projects](examples/java/io/mailtrap/examples/testing/Projects.java)
- [Send mail using template](examples/java/io/mailtrap/examples/testing/Email.java)

### Bulk sending API

- [Send mail](examples/java/io/mailtrap/examples/bulk/BulkSend.java)

## Contributing

Bug reports and pull requests are welcome on [GitHub](https://github.com/railsware/mailtrap-java). This project is intended to be a safe, welcoming space for collaboration, and contributors are expected to adhere to the [code of conduct](CODE_OF_CONDUCT.md).

## License

The package is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).

## Code of Conduct

Everyone interacting in the Mailtrap project's codebases, issue trackers, chat rooms and mailing lists is expected to follow the [code of conduct](CODE_OF_CONDUCT.md).
