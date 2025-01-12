package kz.otussocialnetwork.security.adapter;

import kz.otussocialnetwork.security.model.Authentication;

public interface AuthAdapter {

  void setAuthentication(Authentication authentication);

  Authentication getAuthentication();
}
