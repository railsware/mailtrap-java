package io.mailtrap.model.request.companyinfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCompanyInfoRequest extends AbstractModel {

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

        @JsonProperty("zip_code")
        private String zipCode;

        @JsonProperty("website_url")
        private String websiteUrl;

        private String phone;

        @JsonProperty("privacy_policy_url")
        private String privacyPolicyUrl;

        @JsonProperty("terms_of_service_url")
        private String termsOfServiceUrl;

        @JsonProperty("info_level")
        private String infoLevel;

    }

}
