package io.mailtrap.model.response.messages;

import lombok.Data;

@Data
public class MessageSpamScoreResponse {

    private SpamScoreReport report;
}
