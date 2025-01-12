package kz.otussocialnetwork.repository.user_repository;

import java.util.List;
import java.util.Optional;
import kz.otussocialnetwork.model.entity.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
  private static final String TABLE_NAME = "users";

  private final JdbcTemplate jdbcTemplate;

  @Override
  public Optional<UserEntity> findById(@NonNull Long id) {

    String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    return null;
  }

  @Override
  public List<UserEntity> findAll() {
    String sql = "SELECT * FROM " + TABLE_NAME;

    return List.of();
  }

  @Override
  public @NonNull UserEntity create(@NonNull UserEntity userEntity) {
    String sql = "INSERT INTO " + TABLE_NAME + " (name, email, password) VALUES (?, ?, ?)";
    return null;
  }

  @Override
  public @NonNull UserEntity update(@NonNull UserEntity userEntity) {

    return null;
  }

  @Override
  public void deleteById(@NonNull Long id) {
    String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
  }
}
