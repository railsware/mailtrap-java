package io.mailtrap.model.response.sending_domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.response.Permission;
import lombok.Data;

import java.util.List;

@Data
public class SendingDomainsResponse {

    private long id;

    @JsonProperty("domain_name")
    private String domainName;

    private boolean demo;

    @JsonProperty("compliance_status")
    private String complianceStatus;

    @JsonProperty("dns_verified")
    private boolean dnsVerified;

    @JsonProperty("dns_records")
    private List<DnsRecord> dnsRecords;

    @JsonProperty("open_tracking_enabled")
    private boolean openTrackingEnabled;

    @JsonProperty("click_tracking_enabled")
    private boolean clickTrackingEnabled;

    @JsonProperty("auto_unsubscribe_link_enabled")
    private boolean autoUnsubscribeLinkEnabled;

    @JsonProperty("custom_domain_tracking_enabled")
    private boolean customDomainTrackingEnabled;

    @JsonProperty("health_alerts_enabled")
    private boolean healthAlertsEnabled;

    @JsonProperty("critical_alerts_enabled")
    private boolean criticalAlertsEnabled;

    @JsonProperty("alert_recipient_email")
    private String alertRecipientEmail;

    @JsonProperty("permissions")
    private SendingDomainPermission permission;

}
