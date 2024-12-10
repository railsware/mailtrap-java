package io.mailtrap.model.response.messages;

import lombok.Data;

import java.util.List;

@Data
public class BlacklistsReportInfo {

    private BlacklistReportInfoResult result;

    private String domain;

    private String ip;

    private List<Report> report;
}
