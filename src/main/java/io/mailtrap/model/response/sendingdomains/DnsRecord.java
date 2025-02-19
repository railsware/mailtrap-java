package io.mailtrap.model.response.sendingdomains;

import lombok.Data;

@Data
public class DnsRecord {

    private String key;

    private String domain;

    private String name;

    private String status;

    private String type;

    private String value;

}
