package kz.otussocialnetwork.model.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Function;
import kz.otussocialnetwork.model.dto.UserSignInRequest;
import kz.otussocialnetwork.model.dto.UserUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class UserEntity {
  public UUID      id;
  public String    username;
  public String    firstName;
  public String    secondName;
  public LocalDate birthDate;
  public String    biography;
  public String    city;
  public String    password;

  public static UserEntity register(@NonNull UserSignInRequest request,
                                    @NonNull Function<String, String> encodeFunction) {
    return of(
      UUID.randomUUID(),
      request.username,
      request.firstName,
      request.secondName,
      request.birthDate,
      request.biography,
      request.city,
      encodeFunction.apply(request.password)
    );
  }

  public void update(@NonNull UserUpdateRequest request,
                     @NonNull Function<String, String> encodeFunction) {
    username   = request.username;
    firstName  = request.firstName;
    secondName = request.secondName;
    birthDate  = request.birthDate;
    biography  = request.biography;
    city       = request.city;
    password   = encodeFunction.apply(request.password);
  }

  public static final class PgFields {
    public static final String id         = "id";
    public static final String username   = "username";
    public static final String firstName  = "first_name";
    public static final String secondName = "second_name";
    public static final String birthDate  = "birth_date";
    public static final String biography  = "biography";
    public static final String city       = "city";
    public static final String password   = "password";
  }

  @NoArgsConstructor(staticName = "of")
  @Component
  public static final class UserRowMapper implements RowMapper<UserEntity> {

    @Override public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
      UserEntity userEntity = new UserEntity();

      userEntity.id         = rs.getObject(PgFields.id, UUID.class);
      userEntity.username   = rs.getString(PgFields.username);
      userEntity.firstName  = rs.getString(PgFields.firstName);
      userEntity.secondName = rs.getString(PgFields.secondName);
      userEntity.birthDate  = LocalDate.parse(rs.getString(PgFields.birthDate));
      userEntity.biography  = rs.getString(PgFields.biography);
      userEntity.city       = rs.getString(PgFields.city);
      userEntity.password   = rs.getString(PgFields.password);

      return userEntity;
    }
  }
}
