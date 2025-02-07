package kz.otussocialnetwork.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kz.otussocialnetwork.security.adapter.AuthAdapter;
import kz.otussocialnetwork.security.jwt.exception.TokenInvalidException;
import kz.otussocialnetwork.security.jwt.provider.JwtTokenProvider;
import kz.otussocialnetwork.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthService      authService;
  private final AuthAdapter      authAdapter;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    String requestURI = request.getRequestURI();

    if (requestURI.startsWith("/api/v1/user/create")
      || requestURI.startsWith("/api/v1/auth/log-in")
    ) {
      filterChain.doFilter(request, response);
      return;
    }

    String bearerToken = request.getHeader("Authorization");

    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      bearerToken = bearerToken.substring(7);
    }

    if (bearerToken != null && jwtTokenProvider.validateAccessToken(bearerToken)) {
      authAdapter.setAuthentication(authService.getAuthentication(bearerToken));
    } else {
      throw new TokenInvalidException("TGXodWzhJ", "token invalid");
    }

    try {
      filterChain.doFilter(request, response);
    } finally {
      authAdapter.clearAuthentication();
    }
  }
}
