package kz.otussocialnetwork.security.filter;

import jakarta.servlet.http.HttpServletRequest;
import kz.otussocialnetwork.exception.NotFoundException;
import kz.otussocialnetwork.security.adapter.AuthAdapter;
import kz.otussocialnetwork.security.jwt.provider.JwtTokenProvider;
import kz.otussocialnetwork.security.model.Authentication;
import kz.otussocialnetwork.security.scanner.model.Endpoint;
import kz.otussocialnetwork.security.scanner.model.enums.RequestType;
import kz.otussocialnetwork.security.scanner.repository.EndpointRepository;
import kz.otussocialnetwork.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtFilter implements HttpFilter {
  private final AuthAdapter        authAdapter;
  private final AuthService        authService;
  private final JwtTokenProvider   jwtTokenProvider;
  private final int                order = 0;
  private final EndpointRepository endpointRepository;

  @Override public void accept(@NonNull HttpRequestMetadata metadata) {
    HttpServletRequest request = metadata.request;

    String      requestUri = metadata.request.getRequestURI();
    RequestType methodName = RequestType.fromName(metadata.request.getMethod());

    Endpoint endpoint = endpointRepository.findByUrlAndType(requestUri, methodName)
                                          .orElseThrow(() -> new NotFoundException("8cktT4f", "Endpoint not found"));

    if (endpoint.permitAll) {
      metadata.endpoint    = endpoint;
      metadata.skipCurrent = true;
    }

    String bearerToken = request.getHeader("Authorization");

    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      bearerToken = bearerToken.substring(7);
    }

    if (bearerToken != null && jwtTokenProvider.validateAccessToken(bearerToken)) {
      Authentication authentication = authService.getAuthentication(bearerToken);
      metadata.authentication = authentication;
      authAdapter.setAuthentication(authentication);
    }
  }

  @Override public int order() {
    return order;
  }

  @Override public int compareTo(@NotNull HttpFilter other) {
    return Integer.compare(order, other.order());
  }
}
