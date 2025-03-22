package kz.otussocialnetwork.security.jwt.exception;

import lombok.NonNull;

public class AuthPasswordInvalidException extends RuntimeException {

  public AuthPasswordInvalidException(@NonNull String placeId, @NonNull String message) {
    super(placeId + " :: " + message);
  }

}
