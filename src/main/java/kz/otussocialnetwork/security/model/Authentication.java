package kz.otussocialnetwork.security.model;

import java.util.Set;
import kz.otussocialnetwork.model.enums.Role;

public class Authentication {
  public Long      userId;
  public Set<Role> roles;
}
