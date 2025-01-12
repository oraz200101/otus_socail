package kz.otussocialnetwork.security.context;

import lombok.NonNull;

public interface AuthContextHolder {
  void clearContext();

  void setContext(@NonNull AuthContext authContext);

  AuthContext getContext();
}
