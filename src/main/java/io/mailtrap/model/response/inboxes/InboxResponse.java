package io.mailtrap.model.response.inboxes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.response.Permission;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class InboxResponse {

    private long id;

    private String name;

    private String username;

    private String password;

    @JsonProperty("max_size")
    private int maxSize;

    private String status;

    @JsonProperty("email_username")
    private String emailUsername;

    @JsonProperty("email_username_enabled")
    private boolean emailUsernameEnabled;

    @JsonProperty("sent_messages_count")
    private int sentMessagesCount;

    @JsonProperty("forwarded_messages_count")
    private int forwardedMessagesCount;

    @JsonProperty("used")
    private boolean used;

    @JsonProperty("forward_from_email_address")
    private String forwardFromEmailAddress;

    @JsonProperty("project_id")
    private long projectId;

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("pop3_domain")
    private String pop3Domain;

    @JsonProperty("email_domain")
    private String emailDomain;

    @JsonProperty("api_domain")
    private String apiDomain;

    @JsonProperty("emails_count")
    private int emailsCount;

    @JsonProperty("emails_unread_count")
    private int emailsUnreadCount;

    @JsonProperty("last_message_sent_at")
    private OffsetDateTime lastMessageSentAt;

    @JsonProperty("smtp_ports")
    private List<Integer> smtpPorts;

    @JsonProperty("pop3_ports")
    private List<Integer> pop3Ports;

    @JsonProperty("max_message_size")
    private int maxMessageSize;

    @JsonProperty("permissions")
    private Permission permission;

}
