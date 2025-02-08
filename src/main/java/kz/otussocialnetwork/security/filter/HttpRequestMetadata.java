package kz.otussocialnetwork.security.filter;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import kz.otussocialnetwork.security.model.Authentication;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor(staticName = "of")
public class HttpRequestMetadata {
  public @NonNull  HttpServletRequest request;
  public           boolean            skipCurrent;
  public @Nullable Endpoint           endpoint;
  public @Nullable Authentication     authentication;
  public           boolean            successAll;

  public static HttpRequestMetadata create(HttpServletRequest request) {
    return of(request, false, null, null, false);
  }
}
