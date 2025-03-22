package kz.otussocialnetwork.security.jwt.exception;

import lombok.NonNull;

public class AccessDeniedException extends RuntimeException {

  public AccessDeniedException(@NonNull String placeId, @NonNull String message) {
    super(placeId + " :: " + message);
  }
}
