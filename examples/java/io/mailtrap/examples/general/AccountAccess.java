package io.mailtrap.examples.general;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.account_accesses.ListAccountAccessQueryParams;
import io.mailtrap.model.response.account_accesses.AccountAccessResponse;

import java.util.List;

public class AccountAccess {

    private static final String TOKEN = "<YOUR MAILTRAP TOKEN>";
    private static final long ACCOUNT_ID = 1L;
    private static final String INBOX_ID = "2";

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        ListAccountAccessQueryParams queryParams = new ListAccountAccessQueryParams();
        queryParams.setInboxIds(List.of(INBOX_ID));

        List<AccountAccessResponse> responses = client.generalApi().accountAccesses().listUserAndInviteAccountAccesses(ACCOUNT_ID, queryParams);

        System.out.println(responses);

        System.out.println(client.generalApi().accountAccesses().removeAccountAccess(responses.get(0).getId(), ACCOUNT_ID));
    }
}
