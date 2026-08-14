package io.mailtrap.model.response.emailcampaigns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * The template associated with an email campaign as returned in the campaign response.
 * {@code bodyHtml} and {@code bodyText} are returned only on single-campaign responses; the
 * list endpoint omits them.
 */
@Data
public class Template {

    private Long id;

    private String subject;

    @JsonProperty("merge_tags")
    private List<String> mergeTags;

    @JsonProperty("body_html")
    private String bodyHtml;

    @JsonProperty("body_text")
    private String bodyText;

}
