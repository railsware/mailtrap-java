package io.mailtrap.model.request.companyinfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCompanyInfoRequest extends AbstractModel {

    @JsonProperty("company_info")
    private CompanyInfoData companyInfo;

    @Getter
    @Builder
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CompanyInfoData {

        private String name;

        private String address;

        private String city;

        private String country;

        private String phone;

        @JsonProperty("zip_code")
        private String zipCode;

        @JsonProperty("privacy_policy_url")
        private String privacyPolicyUrl;

        @JsonProperty("terms_of_service_url")
        private String termsOfServiceUrl;

        @JsonProperty("website_url")
        private String websiteUrl;

        @JsonProperty("info_level")
        private String infoLevel;

    }

}
