package kz.otussocialnetwork.repository.base;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public interface CrudRepository<T, ID> {
  Optional<T> findById(@NonNull ID id);

  List<T> findAll();

  @NonNull T create(@NonNull T t);

  void update(@NonNull T t);

  void deleteById(@NonNull ID id);
}
