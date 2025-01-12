package kz.otussocialnetwork.security.context;

import kz.otussocialnetwork.security.model.Authentication;

public interface AuthContext {

  Authentication getAuth();
}
