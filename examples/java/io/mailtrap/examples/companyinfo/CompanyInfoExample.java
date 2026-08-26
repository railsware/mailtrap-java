package io.mailtrap.examples.companyinfo;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.companyinfo.CreateCompanyInfoRequest;
import io.mailtrap.model.request.companyinfo.UpdateCompanyInfoRequest;

public class CompanyInfoExample {

    private static final String TOKEN = System.getenv("MAILTRAP_API_KEY");
    private static final long DOMAIN_ID = Long.parseLong(System.getenv("MAILTRAP_DOMAIN_ID"));

    public static void main(String[] args) {
        final var config = new MailtrapConfig.Builder()
            .token(TOKEN)
            .build();

        final var client = MailtrapClientFactory.createMailtrapClient(config);

        final var createRequest = new CreateCompanyInfoRequest(
            CreateCompanyInfoRequest.CompanyInfoData.builder()
                .name("Mailtrap")
                .address("123 Main St")
                .city("San Francisco")
                .country("US")
                .zipCode("94105")
                .websiteUrl("https://mailtrap.io")
                .phone("+1-555-0100")
                .privacyPolicyUrl("https://mailtrap.io/privacy")
                .termsOfServiceUrl("https://mailtrap.io/terms")
                .infoLevel("business")
                .build()
        );

        final var created = client.sendingApi().companyInfo().createCompanyInfo(DOMAIN_ID, createRequest);
        System.out.println(created.getData());

        final var companyInfo = client.sendingApi().companyInfo().getCompanyInfo(DOMAIN_ID);
        System.out.println(companyInfo.getData());

        final var updateRequest = new UpdateCompanyInfoRequest(
            UpdateCompanyInfoRequest.CompanyInfoData.builder()
                .city("New York")
                .zipCode("10001")
                .build()
        );

        final var updated = client.sendingApi().companyInfo().updateCompanyInfo(DOMAIN_ID, updateRequest);
        System.out.println(updated.getData());
    }
}
