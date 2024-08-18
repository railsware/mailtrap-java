package io.mailtrap.model.response.billing;

import lombok.Data;

@Data
public class BillingResponse {

    private Billing billing;

    private Testing testing;

    private Sending sending;

}
