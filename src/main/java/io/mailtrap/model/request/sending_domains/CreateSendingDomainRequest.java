package io.mailtrap.model.request.sending_domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateSendingDomainRequest extends AbstractModel {

    @JsonProperty("sending_domain")
    private SendingDomainData sendingDomain;

    @Getter
    @AllArgsConstructor
    public static class SendingDomainData {

        @JsonProperty("domain_name")
        private String domainName;

    }

}
