package io.mailtrap.model.response.billing;

import lombok.Data;

@Data
public class UsageCount {

    private int current;

    private int limit;

}
