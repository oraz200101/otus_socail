package kz.otussocialnetwork.annotation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExecutionTimeType {
  SECONDS("seconds"),
  MINUTES("minutes"),
  MILLISECONDS("milliseconds"),
  NANOSECONDS("nanoseconds");

  private final String value;
}
