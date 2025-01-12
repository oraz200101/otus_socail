package kz.otussocialnetwork.model.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class UserEntity {
  private Long      id;
  private String    username;
  private String    firstName;
  private String    secondName;
  private LocalDate birthDate;
  private String    biography;
  private String    city;
  private String    password;
}
