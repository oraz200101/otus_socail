package kz.otussocialnetwork.exception;

import lombok.NonNull;

public class ValidationException extends RuntimeException {

  public ValidationException(@NonNull String placeId, @NonNull String message) {
    super(placeId + " :: " + message);
  }

}
