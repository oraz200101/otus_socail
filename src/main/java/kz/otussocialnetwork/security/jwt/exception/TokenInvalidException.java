package kz.otussocialnetwork.security.jwt.exception;

import lombok.NonNull;

public class TokenInvalidException extends RuntimeException {

  public TokenInvalidException(@NonNull String placeId, @NonNull String message) {
    super(placeId + " :: " + message);
  }

}
