package kz.otussocialnetwork.utils;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDUtil {

  public static UUID rndUUID() {
    return UUID.randomUUID();
  }

  public static UUID fromString(@NonNull String uuid) {
    return UUID.fromString(uuid);
  }
}
