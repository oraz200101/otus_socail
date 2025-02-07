package kz.otussocialnetwork.security.context;

import kz.otussocialnetwork.security.model.Authentication;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
public class AuthContext {
  private final Authentication auth;
}
