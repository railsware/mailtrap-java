package io.mailtrap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

  public enum ContactFieldDataType {
  TEXT("text"),
  INTEGER("integer"),
  FLOAT("float"),
  BOOLEAN("boolean"),
  DATE("date");

  private final String value;

  ContactFieldDataType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @JsonCreator
  public static ContactFieldDataType fromValue(String value) {
    for (ContactFieldDataType type : ContactFieldDataType.values()) {
      if (type.value.equalsIgnoreCase(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown value: " + value);
  }
}
