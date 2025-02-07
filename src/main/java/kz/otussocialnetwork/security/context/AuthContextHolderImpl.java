package kz.otussocialnetwork.security.context;

import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AuthContextHolderImpl implements AuthContextHolder {
  private final ThreadLocal<AuthContext> authContextThreadLocal = new ThreadLocal<>();

  @Override public void clearContext() {
    authContextThreadLocal.remove();
  }

  @Override public void setContext(@NonNull AuthContext authContext) {
    authContextThreadLocal.set(authContext);
  }

  @Override public AuthContext getContext() {
    return authContextThreadLocal.get();
  }
}
