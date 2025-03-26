package kz.otussocialnetwork.security.model;

import java.util.Set;
import java.util.UUID;
import kz.otussocialnetwork.model.enums.Role;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class Authentication {
  public UUID      userId;
  public Set<Role> roles;
}
