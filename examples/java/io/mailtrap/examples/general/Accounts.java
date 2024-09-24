package io.mailtrap.examples.general;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;

public class Accounts {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        System.out.println(client.generalApi().accounts().getAllAccounts());
    }
}
