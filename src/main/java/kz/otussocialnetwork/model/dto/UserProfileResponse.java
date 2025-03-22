package kz.otussocialnetwork.model.dto;

import java.time.LocalDate;
import java.util.UUID;
import kz.otussocialnetwork.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor(staticName = "of")
public class UserProfileResponse {
  public UUID      id;
  public String    username;
  public String    firstName;
  public String    secondName;
  public LocalDate birthDate;
  public String    biography;
  public String    city;

  public static UserProfileResponse fromEntity(@NonNull UserEntity user) {
    return of(
      user.id,
      user.username,
      user.firstName,
      user.secondName,
      user.birthDate,
      user.biography,
      user.city
    );
  }
}
