package io.mailtrap.model.response.billing;

import lombok.Data;

@Data
public class Sending {

    private Plan plan;

    private Usage usage;

}
