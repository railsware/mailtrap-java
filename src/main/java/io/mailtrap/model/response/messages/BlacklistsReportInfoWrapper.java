package io.mailtrap.model.response.messages;

import lombok.Getter;

@Getter
public class BlacklistsReportInfoWrapper {
    private Boolean booleanValue;
    private BlacklistsReportInfo objectValue;

    public BlacklistsReportInfoWrapper(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public BlacklistsReportInfoWrapper(BlacklistsReportInfo objectValue) {
        this.objectValue = objectValue;
    }

    public boolean isBoolean() {
        return booleanValue != null;
    }

}
