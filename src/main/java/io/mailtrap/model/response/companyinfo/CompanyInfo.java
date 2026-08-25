package io.mailtrap.model.response.companyinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyInfo {

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
