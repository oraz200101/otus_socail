package kz.otussocialnetwork.security.scanner.model.enums;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RequestType {
  GET("GetMapping"),
  POST("PostMapping"),
  PUT("PutMapping"),
  DELETE("DeleteMapping"),
  PATCH("PatchMapping"),
  ;

  public final String annotationName;

  public static RequestType fromAnnotationName(@NonNull String annotationName) {
    return switch (annotationName) {
      case "GetMapping" -> RequestType.GET;
      case "PostMapping" -> RequestType.POST;
      case "PutMapping" -> RequestType.PUT;
      case "PatchMapping" -> RequestType.PATCH;
      case "DeleteMapping" -> RequestType.DELETE;
      default -> throw new IllegalArgumentException("tuMxVwLU :: Unsupported annotation: " + annotationName);
    };
  }
}
