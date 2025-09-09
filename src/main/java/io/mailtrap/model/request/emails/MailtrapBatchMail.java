package io.mailtrap.model.request.emails;

import io.mailtrap.model.AbstractModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MailtrapBatchMail extends AbstractModel {

    private BatchEmailBase base;

    private List<MailtrapMail> requests;

}
