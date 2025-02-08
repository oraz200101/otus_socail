package kz.otussocialnetwork.security.filter;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class SecurityFilterChain extends OncePerRequestFilter {
  private final List<HttpFilter> filterList;

  @PostConstruct
  public void sortFilters() {
    filterList.sort(HttpFilter::compareTo);
  }

  @Override protected void doFilterInternal(@NonNull HttpServletRequest request,
                                            @NonNull HttpServletResponse response,
                                            @NonNull FilterChain filterChain) throws ServletException, IOException {
    HttpRequestMetadata httpRequestMetadata = HttpRequestMetadata.create(request);

    filterList.forEach(filter -> filter.accept(httpRequestMetadata));

    if (httpRequestMetadata.successAll) {
      filterChain.doFilter(request, response);
    } else {
      throw new RuntimeException("G6BVuPeSP :: auth failed");
    }
  }
}
