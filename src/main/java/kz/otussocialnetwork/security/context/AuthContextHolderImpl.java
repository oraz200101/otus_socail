package kz.otussocialnetwork.security.context;

import lombok.NonNull;

public class AuthContextHolderImpl implements AuthContextHolder, AutoCloseable {
  private static final ThreadLocal<AuthContext> authContextThreadLocal = new ThreadLocal<>();

  @Override public void clearContext() {
    authContextThreadLocal.remove();
  }

  @Override public void setContext(@NonNull AuthContext authContext) {
    authContextThreadLocal.set(authContext);
  }

  @Override public AuthContext getContext() {
    return authContextThreadLocal.get();
  }

  @Override public void close() {
    clearContext();
  }
}
