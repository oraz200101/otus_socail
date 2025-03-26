package kz.otussocialnetwork.repository.user_repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kz.otussocialnetwork.model.entity.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static kz.otussocialnetwork.repository.constants.UserPostgresQueries.CREATE_USER;
import static kz.otussocialnetwork.repository.constants.UserPostgresQueries.DELETE_USER_BY_ID;
import static kz.otussocialnetwork.repository.constants.UserPostgresQueries.FIND_ALL_USERS;
import static kz.otussocialnetwork.repository.constants.UserPostgresQueries.FIND_USER_BY_ID;
import static kz.otussocialnetwork.repository.constants.UserPostgresQueries.FIND_USER_BY_USERNAME;
import static kz.otussocialnetwork.repository.constants.UserPostgresQueries.SEARCH_USER_BY_FIRSTNAME_AND_SECOND_NAME;
import static kz.otussocialnetwork.repository.constants.UserPostgresQueries.UPDATE_USER;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
  private final JdbcTemplate             jdbcTemplate;
  private final UserEntity.UserRowMapper userRowMapper;

  @Override
  public Optional<UserEntity> findById(@NonNull UUID id) {
    UserEntity userEntity = jdbcTemplate.queryForObject(
      FIND_USER_BY_ID,
      userRowMapper,
      id
    );

    return Optional.ofNullable(userEntity);
  }

  @Override
  public List<UserEntity> findAll() {
    return jdbcTemplate.query(
      FIND_ALL_USERS,
      userRowMapper
    );
  }

  @Override
  public @NonNull UserEntity create(@NonNull UserEntity userEntity) {
    jdbcTemplate.update(
      CREATE_USER,
      userEntity.id,
      userEntity.username,
      userEntity.firstName,
      userEntity.secondName,
      userEntity.birthDate,
      userEntity.biography,
      userEntity.city,
      userEntity.password
    );

    return userEntity;
  }

  @Override
  public void update(@NonNull UserEntity userEntity) {
    jdbcTemplate.update(
      UPDATE_USER,
      userEntity.username,
      userEntity.firstName,
      userEntity.secondName,
      userEntity.birthDate,
      userEntity.biography,
      userEntity.city,
      userEntity.password,
      userEntity.id
    );

  }

  @Override
  public void deleteById(@NonNull UUID id) {
    jdbcTemplate.update(DELETE_USER_BY_ID, id);
  }

  @Override public Optional<UserEntity> findByUsername(@NonNull String username) {
    UserEntity userEntity = jdbcTemplate.queryForObject(
      FIND_USER_BY_USERNAME,
      userRowMapper,
      username
    );

    return Optional.ofNullable(userEntity);
  }

  @Override public List<UserEntity> findByFirstNameAndSecondName(@Nullable String firstName,
                                                                 @Nullable String secondName) {

    return jdbcTemplate.query(
      SEARCH_USER_BY_FIRSTNAME_AND_SECOND_NAME,
      userRowMapper,
      firstName,
      secondName
    );
  }

}
