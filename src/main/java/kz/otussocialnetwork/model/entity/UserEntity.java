package kz.otussocialnetwork.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class UserEntity implements UserDetails {
  private Long      id;
  private String    firstName;
  private String    secondName;
  private LocalDate birthDate;
  private String    biography;
  private String    city;
  private String    password;

  @Override public Set<? extends GrantedAuthority> getAuthorities() {
    return Set.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override public String getUsername() {
    return "";
  }
}
