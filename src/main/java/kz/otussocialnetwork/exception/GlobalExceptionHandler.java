package kz.otussocialnetwork.exception;

import kz.otussocialnetwork.security.jwt.exception.AccessDeniedException;
import kz.otussocialnetwork.security.jwt.exception.AuthPasswordInvalidException;
import kz.otussocialnetwork.security.jwt.exception.TokenInvalidException;
import kz.otussocialnetwork.security.jwt.exception.TokenInvalidTypeException;
import lombok.NonNull;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleException(@NonNull Exception ex) {
    return ProblemDetail.forStatusAndDetail(
      HttpStatusCode.valueOf(500), ex.getMessage()
    );
  }

  @ExceptionHandler(NotFoundException.class)
  public ProblemDetail handleNotFoundException(@NonNull Exception ex) {
    return ProblemDetail.forStatusAndDetail(
      HttpStatusCode.valueOf(404), ex.getMessage()
    );
  }

  @ExceptionHandler(BadRequestException.class)
  public ProblemDetail handleBadRequestException(@NonNull Exception ex) {
    return ProblemDetail.forStatusAndDetail(
      HttpStatusCode.valueOf(400), ex.getMessage()
    );
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ProblemDetail handleAccessDeniedException(@NonNull Exception ex) {
    return ProblemDetail.forStatusAndDetail(
      HttpStatusCode.valueOf(403), ex.getMessage()
    );
  }

  @ExceptionHandler(AuthPasswordInvalidException.class)
  public ProblemDetail handleAuthPasswordInvalidException(@NonNull Exception ex) {
    return ProblemDetail.forStatusAndDetail(
      HttpStatusCode.valueOf(401), ex.getMessage()
    );
  }

  @ExceptionHandler(TokenInvalidTypeException.class)
  public ProblemDetail handleTokenInvalidTypeException(@NonNull Exception ex) {
    return ProblemDetail.forStatusAndDetail(
      HttpStatusCode.valueOf(401), ex.getMessage()
    );
  }

  @ExceptionHandler(TokenInvalidException.class)
  public ProblemDetail handleTokenInvalidException(@NonNull Exception ex) {
    return ProblemDetail.forStatusAndDetail(
      HttpStatusCode.valueOf(401), ex.getMessage()
    );
  }

  @ExceptionHandler(ValidationException.class)
  public ProblemDetail handleValidationException(@NonNull Exception ex) {
    return ProblemDetail.forStatusAndDetail(
      HttpStatusCode.valueOf(400), ex.getMessage()
    );
  }
}
