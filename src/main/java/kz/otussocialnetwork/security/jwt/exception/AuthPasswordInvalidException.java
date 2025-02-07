package kz.otussocialnetwork.security.jwt.exception;

import lombok.NonNull;

public class AuthPasswordInvalidException extends RuntimeException {

  public AuthPasswordInvalidException(@NonNull String place, @NonNull String message) {
    super(place + " :: " + message);
  }

}
