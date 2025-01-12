package kz.otussocialnetwork.security.jwt.exception;

import lombok.NonNull;

public class TokenInvalidTypeException extends RuntimeException {
  private final String placeId;

  public TokenInvalidTypeException(@NonNull String placeId, @NonNull String message) {
    super(message);
    this.placeId = placeId;
  }
}
