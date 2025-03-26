package kz.otussocialnetwork.repository.user_repository;

import jakarta.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kz.otussocialnetwork.model.entity.UserEntity;
import kz.otussocialnetwork.repository.base.CrudRepository;
import lombok.NonNull;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {

  Optional<UserEntity> findByUsername(@NonNull String username);

  List<UserEntity> findByFirstNameAndSecondName(@Nullable String firstName,
                                                @Nullable String secondName);
}
