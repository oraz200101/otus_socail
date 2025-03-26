package kz.otussocialnetwork.model.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequest {
  public String    username;
  public String    firstName;
  public String    secondName;
  public LocalDate birthDate;
  public String    biography;
  public String    city;
  public String    password;
}
