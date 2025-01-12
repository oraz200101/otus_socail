package kz.otussocialnetwork.security.context;

import kz.otussocialnetwork.security.model.Authentication;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class AuthContextImpl implements AuthContext {
  private final Authentication auth;

  @Override public Authentication getAuth() {
    return this.auth;
  }
}
