package io.mailtrap.model.response.contactimports;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContactImportStatus {
  CREATED("created"),
  STARTED("started"),
  FINISHED("finished"),
  FAILED("failed"),;

  private final String value;

  ContactImportStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @JsonCreator
  public static ContactImportStatus fromValue(Object value) {
    for (ContactImportStatus level : ContactImportStatus.values()) {
      if (level.value.equalsIgnoreCase((String) value)) {
        return level;
      }
    }

    throw new IllegalArgumentException("Unknown value: " + value);
  }
}
