package kz.otussocialnetwork.exception;

import lombok.NonNull;

public class NotFoundException extends RuntimeException {

  public NotFoundException(@NonNull String placeId,
                           @NonNull String message) {
    super(placeId + " :: " + message);
  }
}
