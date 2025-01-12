package kz.otussocialnetwork.security.adapter;

import kz.otussocialnetwork.security.context.AuthContextHolder;
import kz.otussocialnetwork.security.context.AuthContextImpl;
import kz.otussocialnetwork.security.model.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthAdapterImpl implements AuthAdapter {
  private final AuthContextHolder authContextHolder;

  @Override public void setAuthentication(Authentication authentication) {
    authContextHolder.setContext(AuthContextImpl.of(authentication));
  }

  @Override public Authentication getAuthentication() {
    return authContextHolder.getContext().getAuth();
  }
}
