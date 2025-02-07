package kz.otussocialnetwork.security.jwt.exception;

import lombok.NonNull;

public class TokenInvalidTypeException extends RuntimeException {

  public TokenInvalidTypeException(@NonNull String placeId, @NonNull String message) {
    super(placeId + " :: " + message);
  }

}
