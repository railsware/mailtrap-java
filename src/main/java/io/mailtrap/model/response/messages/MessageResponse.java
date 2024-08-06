package io.mailtrap.model.response.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("inbox_id")
    private int inboxId;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("sent_at")
    private String sentAt;

    @JsonProperty("from_email")
    private String fromEmail;

    @JsonProperty("from_name")
    private String fromName;

    @JsonProperty("to_email")
    private String toEmail;

    @JsonProperty("to_name")
    private String toName;

    @JsonProperty("email_size")
    private int emailSize;

    @JsonProperty("is_read")
    private boolean isRead;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("html_body_size")
    private int htmlBodySize;

    @JsonProperty("text_body_size")
    private int textBodySize;

    @JsonProperty("human_size")
    private String humanSize;

    @JsonProperty("html_path")
    private String htmlPath;

    @JsonProperty("txt_path")
    private String txtPath;

    @JsonProperty("raw_path")
    private String rawPath;

    @JsonProperty("download_path")
    private String downloadPath;

    @JsonProperty("html_source_path")
    private String htmlSourcePath;

    @JsonProperty("blacklists_report_info")
    private boolean blacklistsReportInfo;

    @JsonProperty("smtp_information")
    private SmtpInformation smtpInformation;

}
