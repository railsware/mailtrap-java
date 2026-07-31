package io.mailtrap.model.response.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InboundThreadsListResponse {

    @JsonProperty("data")
    private List<InboundThread> data;

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("last_id")
    private String lastId;
}
