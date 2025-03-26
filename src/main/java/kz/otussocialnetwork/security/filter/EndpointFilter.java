package kz.otussocialnetwork.security.filter;

import java.util.Objects;
import kz.otussocialnetwork.security.jwt.exception.AccessDeniedException;
import kz.otussocialnetwork.security.model.Authentication;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EndpointFilter implements HttpFilter {
  private final int order = 1;

  @Override
  public void accept(@NonNull HttpRequestMetadata metadata) {
    if (metadata.skipCurrent) {
      metadata.successAll = true;
      return;
    }

    Endpoint       endpoint       = Objects.requireNonNull(metadata.endpoint, "Endpoint is required");
    Authentication authentication = Objects.requireNonNull(metadata.authentication, "Authentication is required");

    if (!endpoint.defaultAccessRoles.isEmpty()
      && endpoint.defaultAccessRoles.stream()
                                    .noneMatch(authentication.getRoles()::contains)) {
      throw new AccessDeniedException("7qQIFLcCu", "Access denied");
    }
  }


  @Override public int order() {
    return order;
  }

  @Override public int compareTo(@NotNull HttpFilter other) {
    return Integer.compare(order, other.order());
  }
}
