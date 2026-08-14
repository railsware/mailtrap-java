package io.mailtrap.model.request.emailcampaigns;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Inline email template — the campaign's subject and design. Each campaign has exactly one
 * template, created together with it. On update, the template is always edited in place, and
 * only the sub-fields that are provided change.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateAttributes extends AbstractModel {

    /**
     * Email subject line. Required when creating a campaign. Supports merge tags, e.g.
     * {@code Hi {{first_name}}}.
     */
    private String subject;

    /**
     * HTML body of the email (the design). Optional for a draft; required before the campaign
     * can be scheduled or started. Include an unsubscribe link via an anchor whose {@code href}
     * contains the {@code __unsubscribe_url__} placeholder.
     */
    @JsonProperty("body_html")
    private String bodyHtml;

    /**
     * Optional plain-text alternative of the email body.
     */
    @JsonProperty("body_text")
    private String bodyText;

    /**
     * Bare names of the merge tags referenced in the subject/body, without the {@code {{ }}}
     * delimiters — e.g. {@code ["first_name"]}. Replaced as a whole when provided.
     */
    @JsonProperty("merge_tags")
    private List<String> mergeTags;

}
