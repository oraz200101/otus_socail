package kz.otussocialnetwork.model.dto;

import java.time.LocalDate;
import kz.otussocialnetwork.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor(staticName = "of")
public class UserProfileResponse {
  public String    username;
  public String    firstName;
  public String    secondName;
  public LocalDate birthDate;
  public String    biography;
  public String    city;

  public static UserProfileResponse fromEntity(@NonNull UserEntity user) {
    return of(user.username,
              user.firstName,
              user.secondName,
              user.birthDate,
              user.biography,
              user.city
    );
  }
}
